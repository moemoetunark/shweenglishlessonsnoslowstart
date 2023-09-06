package siam.moemoetun.com.shwedailyenglish.utility;
import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;
import siam.moemoetun.com.shwedailyenglish.R;
public class ToolbarUtils {
    public static void setupToolbarWithCustomFont(
            AppCompatActivity activity, Toolbar toolbar, String title, String fontPath) {
        // Set the toolbar as the support action bar
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false); // Hide default title
        }
        TextView customTitle = toolbar.findViewById(R.id.toolbar_title);
        Typeface customFont = Typeface.createFromAsset(activity.getAssets(), fontPath);
        customTitle.setText(title);
        customTitle.setTypeface(customFont);
    }
}
