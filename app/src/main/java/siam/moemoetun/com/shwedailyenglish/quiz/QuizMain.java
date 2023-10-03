package siam.moemoetun.com.shwedailyenglish.quiz;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.Objects;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.quiz.frag.ContentFragment;
import siam.moemoetun.com.shwedailyenglish.quiz.frag.QuizPlayFragment;
import siam.moemoetun.com.shwedailyenglish.utility.AdMobHelper;

public class QuizMain extends AppCompatActivity {
  AdMobHelper adMobHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        /**
         *Ad Units should be in the type of IronSource.Ad_Unit.AdUnitName, example
         */



        Toolbar toolbar = findViewById(R.id.toolbar);
        String selectedCategory = getIntent().getStringExtra("selectedCategory");
        toolbar.setTitle(selectedCategory);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Create the adapter that will provide the fragments for each tab
        QuizPagerAdapter pagerAdapter = new QuizPagerAdapter(getSupportFragmentManager(), getLifecycle(), selectedCategory);
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.setUserInputEnabled(false);

        // Connect the TabLayout with the ViewPager
        //tabLayout.setupWithViewPager(viewPager);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    // Set the tab titles
                    switch (position) {
                        case 0:
                            tab.setText("Explanations-ရှင်းလင်းချက်");
                            break;
                        case 1:
                            tab.setText("Exercises-လေ့ကျင့်ခန်း");
                            break;
                    }
                }).attach();
    }

    public static class QuizPagerAdapter extends FragmentStateAdapter {
        private static final int NUM_TABS = 2;
        private final String selectedCategory;

        public QuizPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, String selectedCategory) {
            super(fragmentManager, lifecycle);
            this.selectedCategory = selectedCategory;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Return the appropriate fragment based on the position
            switch (position) {
                case 0:
                    return ContentFragment.newInstance(selectedCategory);
                case 1:
                    return QuizPlayFragment.newInstance(selectedCategory);
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            // Return the total number of tabs
            return NUM_TABS;
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
