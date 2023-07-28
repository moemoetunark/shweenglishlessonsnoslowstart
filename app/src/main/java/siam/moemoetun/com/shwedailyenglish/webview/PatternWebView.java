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

public class PatternWebView extends AppCompatActivity {

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

        InterstitialAd.load(PatternWebView.this, getString(R.string.inter_goback),
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
            webView.loadUrl("file:///android_asset/pattern/I-have-something_to.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/pattern/I promise not to + v1.html");
        } else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/pattern/I should have + V3.html");
        } else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/pattern/It is too bad that + s + v+o.html");
        } else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/pattern/I don't have time to + V1.html");
        } else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/pattern/I am calling to + V1.html");
        } else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/pattern/I am dying to + V1.html");
        } else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/pattern/I am having hard time + V-ing.html");
        } else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/pattern/I am sorry to + V1.html");
        } else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/pattern/I am thinking of +V-ing.html");
        } else if (intExtra == 10) {
            webView.loadUrl("file:///android_asset/pattern/I am working on + noun.html");
        } else if (intExtra == 11) {
            webView.loadUrl("file:///android_asset/pattern/I'll help you+ V1.html");
        } else if (intExtra == 12) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Be + adj.htm");
        } else if (intExtra == 13) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Giving order with V1.htm");
        } else if (intExtra == 14) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/gonna+gotta+wanna.htm");
        } else if (intExtra == 15) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/I am here to + V1.htm");
        } else if (intExtra == 16) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/I am sure to + V1.htm");
        } else if (intExtra == 17) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/If I were you.htm");
        } else if (intExtra == 18) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/If.htm");
        } else if (intExtra == 19) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/It is time to + V1.htm");
        } else if (intExtra == 20) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Let O + V1.htm");
        } else if (intExtra == 21) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/May I + V1.htm");
        } else if (intExtra == 22) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/No need to + V1.htm");
        } else if (intExtra == 23) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Not because but because.htm");
        } else if (intExtra == 24) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Not only but also.htm");
        } else if (intExtra == 25) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Nothing to + V1.htm");
        } else if (intExtra == 26) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Please + V1..htm");
        } else if (intExtra == 27) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Please don't + V1.htm");
        } else if (intExtra == 28) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + can + V1.htm");
        } else if (intExtra == 29) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/s + can't help + V-ing.htm");
        } else if (intExtra == 30) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + dare + V1.htm");
        } else if (intExtra == 31) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + feel + Adj.htm");
        } else if (intExtra == 32) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + have to + V1.htm");
        } else if (intExtra == 33) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + linking verb + adj.htm");
        } else if (intExtra == 34) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + make O + V1.htm");
        } else if (intExtra == 35) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + need to + V1.htm");
        } else if (intExtra == 36) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/s + seem + adj.htm");
        } else if (intExtra == 37) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + seem + V1.htm");
        } else if (intExtra == 38) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S + would like O + to V1.htm");
        } else if (intExtra == 39) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ be able to + V1.htm");
        } else if (intExtra == 40) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ be about to + V1.htm");
        } else if (intExtra == 41) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ be going to + V1.htm");
        } else if (intExtra == 42) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ should had better ought to +v1.htm");
        } else if (intExtra == 43) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ used to + V1.htm");
        } else if (intExtra == 44) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ used to + V-ing.htm");
        } else if (intExtra == 45) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+ want O to + V1.htm");
        } else if (intExtra == 46) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+make O + V1.htm");
        } else if (intExtra == 47) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/s+must + V1.htm");
        } else if (intExtra == 48) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/S+would like to+ V1.htm");
        } else if (intExtra == 49) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/That's why .htm");
        } else if (intExtra == 50) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/There is something wrong with.htm");
        } else if (intExtra == 51) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/want to + V1.htm");
        } else if (intExtra == 52) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/what to + V1.htm");
        } else if (intExtra == 53) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Whether or not.htm");
        } else if (intExtra == 54) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Would you like + Noun.htm");
        } else if (intExtra == 55) {
            webView.loadUrl("file:///android_asset/asset_new/pattern/Would you like to + V1.htm");
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
            mInterstitialAd.show(PatternWebView.this);
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
