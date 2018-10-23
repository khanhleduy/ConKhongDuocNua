package l.com.ldk.duykhanh.conkhongduocnua.COURSE.ADAPTER;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adapter_tab extends FragmentStatePagerAdapter {
    List<Fragment> listFragment = new ArrayList<>();
    List<String> titleFragment = new ArrayList<>();

    public Adapter_tab(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return listFragment.get(i);
    }

    @Override
    public int getCount() {

        return titleFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
    public void addFragment(Fragment fragment, String  title){
        listFragment.add(fragment);
        titleFragment.add(title);
    }


}

