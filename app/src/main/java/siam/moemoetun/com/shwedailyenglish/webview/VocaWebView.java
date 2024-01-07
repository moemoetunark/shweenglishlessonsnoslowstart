package siam.moemoetun.com.shwedailyenglish.webview;
import android.content.res.AssetManager;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.utility.ToolbarUtils;

public class VocaWebView extends AppCompatActivity {
    TextView textView;
    Typeface typeface;
    public InputStream input;


    private final String[] assetFiles = {
            "asset_new/make_collocation.txt",
            "asset_new/give-keep-break.txt",
            "asset_new/go-get.txt",
            "asset_new/family_vocabulary.txt",
            "asset_new/Weather_Vocabulary.txt",
            "asset_new/health_problem.txt",
            "asset_new/Animal Vocabulary.txt",
            "asset_new/partofbody.txt",
            "asset_new/Positive Personality Adjectives.txt",
            "asset_new/Actions and Feelings.txt",
            "asset_new/Daily Routine.txt",
            "asset_new/money.txt",
            "asset_new/foods.txt",
            "asset_new/transportation.txt",
            "asset_new/job_vocabulary.txt",
            "asset_new/sport.txt",
            "asset_new/movies.txt",
            "asset_new/music vocabulary.txt",
            "asset_new/politics.txt",
            "asset_new/crimes.txt",
            "asset_new/environmental issues.txt",
            "asset_new/ministery in Myanmar.txt"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voca_textview);
        AdView mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView = new AdView(this);
        mAdView.setAdUnitId(getString(R.string.banner_2021));


        Toolbar toolbar = findViewById(R.id.toolbar);
        ToolbarUtils.setupToolbarWithCustomFont(
                this,
                toolbar,
                getIntent().getStringExtra("clickedItemName"),
                "fonts/tharlon.ttf" // Replace with your font path
        );
        textView = findViewById(R.id.textView2);
        AssetManager assets = getAssets();

        int intExtra = getIntent().getIntExtra("key", 0);
        if (intExtra >= 0 && intExtra < assetFiles.length) {
            try {
                this.input = assets.open(assetFiles[intExtra]);
                byte[] bArr = new byte[this.input.available()];
                this.input.read(bArr);
                this.input.close();
                textView.setText(new String(bArr));
                typeface = Typeface.createFromAsset(getAssets(), "fonts/zawgyi.ttf");
                textView.setTypeface(typeface);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
