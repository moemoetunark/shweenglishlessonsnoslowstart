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

public class InterchangeWeb extends AppCompatActivity {

    public WebView webView;
    private InterstitialAd mInterstitialAd;
    private FrameLayout adContainerView;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
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


        InterstitialAd.load(InterchangeWeb.this, getString(R.string.inter_goback),
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
        int intExtra2 = getIntent().getIntExtra("key1", 0);

        if (intExtra == 0 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level1/1-nice-to-meet-you.html");
        }
        if (intExtra == 0 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level1/2-he's-over-there.html");
        }
        if (intExtra == 0 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/inter/level1/3-it's-interesting.html");
        }
        if (intExtra == 0 && intExtra2 == 3) {
            webView.loadUrl("file:///android_asset/inter/level1/4-oh-no.html");
        }
        if (intExtra == 0 && intExtra2 == 4) {
            webView.loadUrl("file:///android_asset/inter/level1/5-are-you-from-seoul.html");
        }
        if (intExtra == 0 && intExtra2 == 5) {
            webView.loadUrl("file:///android_asset/inter/level1/6-he-is-cute.html");
        }
        if (intExtra == 0 && intExtra2 == 6) {
            webView.loadUrl("file:///android_asset/inter/level1/7-it-is-a-disaster.html");
        }
        if (intExtra == 0 && intExtra2 == 7) {
            webView.loadUrl("file:///android_asset/inter/level1/8-it's-really-cold.html");
        }
        if (intExtra == 0 && intExtra2 == 8) {
            webView.loadUrl("file:///android_asset/inter/level1/9-what-time-is-it-there.html");
        }
        if (intExtra == 0 && intExtra2 == 9) {
            webView.loadUrl("file:///android_asset/inter/level1/10i-am-really-hungry.html");
        }
        if (intExtra == 0 && intExtra2 == 10) {
            webView.loadUrl("file:///android_asset/inter/level1/11-a-nice-car.html");
        }
        if (intExtra == 0 && intExtra2 == 11) {
            webView.loadUrl("file:///android_asset/inter/level1/12-get-up-late.html");
        }
        if (intExtra == 0 && intExtra2 == 12) {
            webView.loadUrl("file:///android_asset/inter/level1/13-my-new-apartment.html");
        }
        if (intExtra == 0 && intExtra2 == 13) {
            webView.loadUrl("file:///android_asset/inter/level1/14-there-arent-any-chair.html");
        }
        if (intExtra == 0 && intExtra2 == 14) {
            webView.loadUrl("file:///android_asset/inter/level1/15-he-works-in-a-hotel.html");
        }
        if (intExtra == 0 && intExtra2 == 15) {
            webView.loadUrl("file:///android_asset/inter/level1/16-please-be-careful.html");
        }
        if (intExtra == 0 && intExtra2 == 16) {
            webView.loadUrl("file:///android_asset/inter/level1/17-how-about-some-sandwich.html");
        }
        if (intExtra == 0 && intExtra2 == 17) {
            webView.loadUrl("file:///android_asset/inter/level1/18-fish-for-breakfast.html");
        }
        if (intExtra == 0 && intExtra2 == 18) {
            webView.loadUrl("file:///android_asset/inter/level1/19-i-love-sports.html");
        }
        if (intExtra == 0 && intExtra2 == 19) {
            webView.loadUrl("file:///android_asset/inter/level1/20-i-can't-sing.html");
        }
        if (intExtra == 0 && intExtra2 == 20) {
            webView.loadUrl("file:///android_asset/inter/level1/21-birthday-plan.html");
        }
        if (intExtra == 0 && intExtra2 == 21) {
            webView.loadUrl("file:///android_asset/inter/level1/22-v-day.html");
        }
        if (intExtra == 0 && intExtra2 == 22) {
            webView.loadUrl("file:///android_asset/inter/level1/23-i-don't-feel-well.html");
        }
        if (intExtra == 0 && intExtra2 == 23) {
            webView.loadUrl("file:///android_asset/inter/level1/24-don't-work-too-hard.html");
        }
        if (intExtra == 0 && intExtra2 == 24) {
            webView.loadUrl("file:///android_asset/inter/level1/25-it-emergency.html");
        }
        if (intExtra == 0 && intExtra2 == 25) {
            webView.loadUrl("file:///android_asset/inter/level1/26-is-it-far-from-here.html");
        }
        if (intExtra == 1 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/1-please-call-me-beth.html");
        }
        if (intExtra == 1 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/2-what-seoul-like.html");
        }
        if (intExtra == 1 && intExtra2 == 2) {
            webView.loadUrl("file:///android_asset/inter/level2/3-how-is-it-going.html");
        }
        if (intExtra == 2 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/4-where-do-you-work.html");
        }
        if (intExtra == 2 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/5-i-start-work-at-five.html");
        }
        if (intExtra == 3 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/6-it-is-really-pretty.html");
        }
        if (intExtra == 3 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/7-i-prefer-the-blue-one.html");
        }
        if (intExtra == 4 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/8-who-is-your-favorite-singer.html");
        }
        if (intExtra == 4 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/9-an-invitation.html");
        }
        if (intExtra == 5 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/10-askin-about-family.html");
        }
        if (intExtra == 5 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/11-is-that-typical.html");
        }
        if (intExtra == 6 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/12-i-hardly-ever-exercise.html");
        }
        if (intExtra == 6 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/13-i-am-a-real-fitness-freak.html");
        }
        if (intExtra == 7 && intExtra2 == 0) {
            webView.loadUrl("file:///android_asset/inter/level2/12-i-hardly-ever-exercise.html");
        }
        if (intExtra == 7 && intExtra2 == 1) {
            webView.loadUrl("file:///android_asset/inter/level2/13-i-am-a-real-fitness-freak.html");
        }


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



    @Override
    public void onBackPressed() {

        if (mInterstitialAd !=null) {
            mInterstitialAd.show(InterchangeWeb.this);
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
}
