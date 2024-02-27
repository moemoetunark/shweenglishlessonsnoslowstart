package siam.moemoetun.com.shwedailyenglish;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import java.util.HashMap;
import siam.moemoetun.com.shwedailyenglish.adapter.ViewPagerAdapter;
import siam.moemoetun.com.shwedailyenglish.constant.AppConstant;
import siam.moemoetun.com.shwedailyenglish.note.NoteActivity;
import siam.moemoetun.com.shwedailyenglish.utility.AdMobHelper;
import siam.moemoetun.com.shwedailyenglish.utility.CustomDialog;
import siam.moemoetun.com.shwedailyenglish.utility.GoogleMobileAdsConsentManager;
import siam.moemoetun.com.shwedailyenglish.utility.NavigationHandler;
public class MainActivity extends AppCompatActivity implements OnUserEarnedRewardListener, NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private AlertDialog dialog;
    private DrawerLayout drawer;
    NavigationView navigationView;
    private AdMobHelper adMobHelper;
    FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
    GoogleMobileAdsConsentManager googleMobileAdsConsentManager;
    private final String[] pageTitle = {"Advanced Grammar","Basic Grammar", "Basic Speaking", "Story", "Vocabulary",
            "Spoken Patterns", "Interchange", "Song Lyrics"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        googleMobileAdsConsentManager = GoogleMobileAdsConsentManager.getInstance(getApplicationContext());
        AppConstant.initialize(getApplicationContext());

        adMobHelper = new AdMobHelper(this, "ca-app-pub-4137439985376631/9876463747");
        adMobHelper.loadRewardedAd();


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
        for (int i = 0; i <8; i++) {
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
        dialog = CustomDialog.createDialog(this);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        int coinBalance = AppConstant.getCoinBalance();
        Menu menu = navigationView.getMenu();
        MenuItem coinBalanceItem = menu.findItem(R.id.coin_balance);
        coinBalanceItem.setTitle("Your coins: " + coinBalance);
        navigationView.invalidate();
        adMobHelper.loadRewardedAd();


    }

    @Override
    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
        Toast.makeText(MainActivity.this, "You earned 10 coins", Toast.LENGTH_SHORT).show();
        int currentBalance = AppConstant.getCoinBalance();
        currentBalance += 10;
        AppConstant.setCoinBalance(currentBalance);

        // Update the coin balance in the coin_balance menu item
        Menu menu = navigationView.getMenu();
        MenuItem coinBalanceItem = menu.findItem(R.id.coin_balance);
        coinBalanceItem.setTitle("Your coins: " + AppConstant.getCoinBalance());
    }

    public void showRewardedAd() {
        adMobHelper.showRewardedAd(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adMobHelper.loadRewardedAd();
        int coinBalance = AppConstant.getCoinBalance();
        Menu menu = navigationView.getMenu();
        MenuItem coinBalanceItem = menu.findItem(R.id.coin_balance);
        coinBalanceItem.setTitle("Your coins: " + coinBalance);
        navigationView.invalidate();

        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful()) {
                    final String new_version_code = mFirebaseRemoteConfig.getString("new_version_code");
                    if (Integer.parseInt(new_version_code) > getVersionCode()) upDateApp();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.privacy_policy) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://shweenglishlessons.blogspot.com/p/privacy-policy.html")));
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
            String url = "https://englishlearning4mm.blogspot.com/2023/03/blog-post.html";
            CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
            intent.launchUrl(MainActivity.this, Uri.parse(url));
        } else if (id ==R.id.foloow_youtube){
            NavigationHandler.openYouTubeChannel(MainActivity.this);
        } else if(id == R.id.get_coins){
            showRewardedAd();
        } else if (id ==R.id.app_exit){
          dialog.show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        MenuItem moreMenu = menu.findItem(R.id.action_more);
        moreMenu.setVisible(googleMobileAdsConsentManager.isPrivacyOptionsRequired());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View menuItemView = findViewById(item.getItemId());
        PopupMenu popup = new PopupMenu(this, menuItemView);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(
                popupMenuItem -> {
                    if (popupMenuItem.getItemId() == R.id.privacy_settings) {
                        // Handle changes to user consent.
                        googleMobileAdsConsentManager.showPrivacyOptionsForm(
                                this,
                                formError -> {
                                    if (formError != null) {
                                        Toast.makeText(this, formError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                        return true;
                    }
                    return false;
                });
        return super.onOptionsItemSelected(item);
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
            Log.i("my log", "NameNotFoundException" + e.getMessage());
        }
        return packageInfo != null ? packageInfo.versionCode : 64;
    }

    @Override
    @SuppressWarnings("MissingSuperCall")
    public void onBackPressed() {
        dialog.show();
    }


}