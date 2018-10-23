package l.com.ldk.duykhanh.conkhongduocnua.COURSE.ACTIVITY;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.ADAPTER.Adapter_tab;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.FRAGMENT.FragmentRegistration;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.FRAGMENT.FragmentShedule;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.FRAGMENT.FragmentTestShedule;
import l.com.ldk.duykhanh.conkhongduocnua.R;

public class CourseActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ActionBar tool;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Học tập");

        final Adapter_tab adapter_tab = new Adapter_tab(getSupportFragmentManager());
        tabLayout = findViewById(R.id.tabs);

        ViewPager viewPager = findViewById(R.id.viewPager);


        adapter_tab.addFragment(new FragmentRegistration(), "Đăng ký học");
        adapter_tab.addFragment(new FragmentShedule(), "Lịch học");
        adapter_tab.addFragment(new FragmentTestShedule(), "Lịch thi");

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorWhite), getResources().getColor(R.color.colorBlack));
        viewPager.setAdapter(adapter_tab);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

