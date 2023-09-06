package siam.moemoetun.com.shwedailyenglish;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

import siam.moemoetun.com.shwedailyenglish.adapter.ViewPagerAdapter;
import siam.moemoetun.com.shwedailyenglish.note.NoteActivity;
import siam.moemoetun.com.shwedailyenglish.utility.NavigationHandler;
public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private DrawerLayout drawer;
    NavigationView navigationView;
    FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
    private NativeAd nativeAds;
    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-4137439985376631/3407696032";
    private AlertDialog alertDialog;
    private final String[] pageTitle = {"Basic Grammar", "Basic Speaking", "Story", "Vocabulary",
            "Spoken Patterns", "Interchange", "Song Lyrics"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(MainActivity.this);
        loadNativeAds();
        HashMap<String, Object> defaultRate = new HashMap<>();
        defaultRate.put("new_version_code", String.valueOf(getVersionCode()));
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(300)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(defaultRate);
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful()) {
                    final String new_version_code = mFirebaseRemoteConfig.getString("new_version_code");
                    if (Integer.parseInt(new_version_code) > getVersionCode()) upDateApp();
                }
            }
        });

        viewPager = findViewById(R.id.viewPager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawerLayout);
        setSupportActionBar(toolbar);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        for (int i = 0; i <7; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
         navigationView = findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.privacy_policy) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.moemoetun.me/p/privacy-policy.html")));
        } else if (id == R.id.rate) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(
                    "https://play.google.com/store/apps/details?id=siam.moemoetun.com.shwedailyenglish"));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        }else if(id == R.id.note){
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            startActivity(intent);
        } else if(id ==R.id.email){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"moemoetun4u@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "အကြောင်းအရာ");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "ရေးချင်သည့်အကြောင်းအပြည့်စုံ");
            // Check if there's an email app available to handle the intent
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            } else {
                Toast.makeText(MainActivity.this, "No email app found.", Toast.LENGTH_SHORT).show();
            }
        } else if(id ==R.id.download){
            String url = "https://www.grammar-owl.de/p/blog-page_7.html";
            CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
            intent.launchUrl(MainActivity.this, Uri.parse(url));
        } else if (id ==R.id.foloow_youtube){
            NavigationHandler.openYouTubeChannel(MainActivity.this);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(nativeAds !=null){
            alertDialog.show();
        }else {
            finish();
        }
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void upDateApp() {
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Update NOW!")
                .setIcon(R.mipmap.app_icon_round)
                .setCancelable(false)
                .setMessage("Newer Version Available!").setPositiveButton("YES!", (dialogInterface, i) -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=siam.moemoetun.com.shwedailyenglish"));
                    startActivity(intent);
                }).show();
    }

    public int getVersionCode() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getCallingPackage(),0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("mylog", "NameNotFoundExcepton" + e.getMessage());
        }
        return packageInfo != null ? packageInfo.versionCode : 55;
    }
    private void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        // Set the media view.
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));
        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every NativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every NativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getMediaContent().getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (nativeAd.getMediaContent() != null && nativeAd.getMediaContent().hasVideoContent()) {

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
                    //refresh.setEnabled(true);
                    //videoStatus.setText("Video status: Video playback has ended.");
                    super.onVideoEnd();
                }
            });
        } else {
            //videoStatus.setText("Video status: Ad does not contain a video asset.");
            //refresh.setEnabled(true);
        }
    }
    public void loadNativeAds(){
        AdLoader.Builder builder = new AdLoader.Builder(this, ADMOB_AD_UNIT_ID);
        builder.forNativeAd(
                new NativeAd.OnNativeAdLoadedListener() {
                    // OnLoadedListener implementation.
                    @Override
                    public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.fullScreenDialog);
                        builder.setTitle("Do you want to exit the app?");
                        // Inflate the custom dialog layout
                        View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_fullscreen_exit_dialog, null);
                        FrameLayout adContainer = dialogView.findViewById(R.id.dialog_content);
                        Button buttonYes = dialogView.findViewById(R.id.button_yes);
                        Button buttonNo = dialogView.findViewById(R.id.button_no);
                        buttonYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
                        buttonNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });
                        // Populate the native ad view
                        NativeAdView adView = (NativeAdView) getLayoutInflater().inflate(R.layout.ad_unified, adContainer, false);
                        populateNativeAdView(nativeAd, adView);
                        populateNativeAdView(nativeAd, adView);
                        MainActivity.this.nativeAds = nativeAd;
                        // Add the ad view to the dialog content
                        adContainer.addView(adView);
                        builder.setView(dialogView);
                        alertDialog = builder.create();
                        Objects.requireNonNull(alertDialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        //Toast.makeText(MainActivity.this, "Failed to load native ad with error ", Toast.LENGTH_SHORT).show();
                    }
                });




        AdLoader adLoader = builder.withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        String error = String.format(Locale.getDefault(),
                                        "domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(),
                                        loadAdError.getCode(),
                                        loadAdError.getMessage());
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());


    }

}