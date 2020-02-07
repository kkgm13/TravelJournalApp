package com.kkgmdevelopments.traveljournalapp.places;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.kkgmdevelopments.traveljournalapp.FragmentPagerAdapter;
import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.holiday.HolidayListAdapter;


public class VisitedPlaceActivity extends AppCompatActivity {

    private Holiday holiday;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        // Toolbar Override set
        String message = getIntent().getStringExtra(HolidayListAdapter.EXTRA_REPLY);
        holiday = (Holiday) getIntent().getSerializableExtra("holiday");
        getSupportActionBar().setTitle(message + " Holiday"); // Override Action Bar title

        // Tabs Creation
        TabLayout tabLayout = findViewById(R.id.tab_list);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.places_tab_text));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.explore_tab_text));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Pager Adapters Initialize
        final ViewPager viewPager = findViewById(R.id.places_pager);
        final FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), context, tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
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
}
