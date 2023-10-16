package siam.moemoetun.com.shwedailyenglish.online.adapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import siam.moemoetun.com.shwedailyenglish.online.tabs.Tab1Fragment;
import siam.moemoetun.com.shwedailyenglish.online.tabs.Tab3Fragment;
public class TabPagerAdapter extends FragmentPagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new Tab1Fragment();
        } else {
            return new Tab3Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}

