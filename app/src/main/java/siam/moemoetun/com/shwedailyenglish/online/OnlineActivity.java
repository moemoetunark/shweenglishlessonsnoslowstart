package siam.moemoetun.com.shwedailyenglish.online;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.adapter.ViewPagerAdapter;
import siam.moemoetun.com.shwedailyenglish.online.adapter.TabPagerAdapter;
public class OnlineActivity extends AppCompatActivity {
    private final String[] pageTitle = {"Basic Grammar", "Basic Speaking", "Story"};
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_tabs);
        viewPager = findViewById(R.id.viewPager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        for (int i = 0; i <3; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //set viewpager adapter
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabPagerAdapter);

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
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }


}
