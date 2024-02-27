package siam.moemoetun.com.shwedailyenglish.webview;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.io.InputStream;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.utility.ToolbarUtils;

public class SongLyrics extends AppCompatActivity {

    TextView textView;

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
        textView.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/zawgyi.ttf"));
        int intExtra = getIntent().getIntExtra("key", 0);
        String[] songLyricFiles = getResources().getStringArray(R.array.song_lyric_files);
        String selectedLyricFile = (intExtra >= 0 && intExtra < songLyricFiles.length) ? songLyricFiles[intExtra] : "";

        try (InputStream input = getAssets().open(selectedLyricFile)) {
            byte[] bArr = new byte[input.available()];
            input.read(bArr);
            textView.setText(new String(bArr));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
