package siam.moemoetun.com.shwedailyenglish.webview;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Objects;

import siam.moemoetun.com.shwedailyenglish.R;

public class StoryWebView extends AppCompatActivity {

public WebView webView;
private InterstitialAd mInterstitialAd;
    private FrameLayout adContainerView;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable navigationIcon = toolbar.getNavigationIcon();
        // Apply color tint to the navigation icon drawable
        if (navigationIcon != null) {
            navigationIcon.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView = new AdView(this);
        mAdView.setAdUnitId(getString(R.string.banner_2021));

        InterstitialAd.load(StoryWebView.this, getString(R.string.inter_goback),
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });

    webView = (WebView)findViewById(R.id.webview);
    webView.getSettings().setJavaScriptEnabled(true);
        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/easy_stories/ababy_and_asock.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/easy_stories/acat_adog.html");
        } else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/easy_stories/an_apple_pie.html");
        } else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/easy_stories/birds_and_ababy.html");
        } else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/easy_stories/birthday_cake.html");
        } else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/easy_stories/boys_willbe_boys.html");
        } else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/easy_stories/in_the_garden.html");
        } else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/easy_stories/no_food_no_job.html");
        } else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/easy_stories/no_friend_forme.html");
        } else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/easy_stories/tell_the_truth.html");
        } else if (intExtra == 10) {
            webView.loadUrl("file:///android_asset/easy_stories/the_baby_bear.html");
        } else if (intExtra == 11) {
            webView.loadUrl("file:///android_asset/easy_stories/Obama.html");
        } else if (intExtra == 12) {
            webView.loadUrl("file:///android_asset/easy_stories/Abraham Lincoln.html");
        } else if (intExtra == 13) {
            webView.loadUrl("file:///android_asset/easy_stories/Bill_gate.html");
        } else if (intExtra == 14) {
            webView.loadUrl("file:///android_asset/easy_stories/Martin Luther King Jr.html");
        } else if (intExtra == 15) {
            webView.loadUrl("file:///android_asset/easy_stories/George Washington.html");
        }

    }



    @Override
    public void onBackPressed() {

        if (mInterstitialAd !=null) {
            mInterstitialAd.show(StoryWebView.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                }
            });
        }
        finish();
    }



    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            this.webView.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
