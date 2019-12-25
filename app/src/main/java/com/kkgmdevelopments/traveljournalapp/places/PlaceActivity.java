package com.kkgmdevelopments.traveljournalapp.places;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.kkgmdevelopments.traveljournalapp.FragmentPagerAdapter;
import com.kkgmdevelopments.traveljournalapp.MainActivity;
import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.holiday.HolidayListAdapter;

public class PlaceActivity extends AppCompatActivity {

    private RecyclerView placeRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        Intent intent = getIntent();

        // Toolbar mechanism
//        Toolbar toolbar = findViewById(R.id.toolbar);
        String message = intent.getStringExtra(HolidayListAdapter.EXTRA_REPLY);
//        toolbar.se(message);


//        getActionBar().setTitle(message);

        // Tabs Creation
        TabLayout tabLayout = findViewById(R.id.tab_list);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.places_tab_text));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.explore_tab_text));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Pager Adapters Initialize
        final ViewPager viewPager = findViewById(R.id.places_pager);
        final FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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

        // Visited Places RecyclerView
        placeRecycler = findViewById(R.id.places_list);
        final VisitedPlaceAdapter placeAdapter = new VisitedPlaceAdapter(this);
//        Issue with RecyclerView Access
//        placeRecycler.setAdapter(placeAdapter);
//        placeRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
