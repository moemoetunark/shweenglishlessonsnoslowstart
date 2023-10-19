package siam.moemoetun.com.shwedailyenglish.adapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import siam.moemoetun.com.shwedailyenglish.fragments.Fragment1;
import siam.moemoetun.com.shwedailyenglish.fragments.Fragment10;
import siam.moemoetun.com.shwedailyenglish.fragments.Fragment2;
import siam.moemoetun.com.shwedailyenglish.fragments.Fragment3;
import siam.moemoetun.com.shwedailyenglish.fragments.Fragment4;
import siam.moemoetun.com.shwedailyenglish.fragments.Fragment5;
import siam.moemoetun.com.shwedailyenglish.fragments.Fragment6;
public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
         if (position ==0) {
            return new Fragment1();
        }else if(position ==1){
            return new Fragment2();
        }else if(position==2){
            return new Fragment3();
        } else if (position ==3) {
            return new Fragment4();
        }else if (position==4){
            return new Fragment5();
        }else if (position==5){
            return new Fragment6();
        }
        else {
            return new Fragment10();
        }
    }

    @Override
    public int getCount() {
        return 7;
    }
}
