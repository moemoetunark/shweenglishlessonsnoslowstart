package siam.moemoetun.com.shwedailyenglish.content;
import static siam.moemoetun.com.shwedailyenglish.utility.FileUtils.removeFileExtension;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.Objects;

import siam.moemoetun.com.shwedailyenglish.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);
        fileName = getIntent().getStringExtra("file_name");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String fileNameWithoutExtension = removeFileExtension(fileName);
        String fileNameToolbar = fileNameWithoutExtension.substring(fileNameWithoutExtension.indexOf("_") + 1);
        toolbar.setTitle(fileNameToolbar);

        Drawable navigationIcon = toolbar.getNavigationIcon();
        // Apply color tint to the navigation icon drawable
        if (navigationIcon != null) {
            navigationIcon.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        // Load the WebView with the selected file
        loadWebViewContent();


    }


    private void loadWebViewContent() {
        Intent intent = getIntent();
        String sourceActivity = intent.getStringExtra("sourceActivity");
        if (sourceActivity != null) {
            switch (sourceActivity) {
                case "MainActivity":
                    webView.loadUrl("file:///android_asset/fileNames/" + fileName);
                    break;
                case "IdiomActivity":
                    webView.loadUrl("file:///android_asset/idioms/" + fileName);
                    break;
                case "RewardActivity":
                    webView.loadUrl("file:///android_asset/rewardItems/" + fileName);
                    break;
                case "Conversation":
                    webView.loadUrl("file:///android_asset/conversation/" + fileName);
                    break;
                case "premium":
                    webView.loadUrl("file:///android_asset/categories/" + fileName);
                    break;
            }
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


}
