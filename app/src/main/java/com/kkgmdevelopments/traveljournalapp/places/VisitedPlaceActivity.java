package com.kkgmdevelopments.traveljournalapp.places;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.kkgmdevelopments.traveljournalapp.FragmentPagerAdapter;
import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.holiday.HolidayListAdapter;

import java.util.List;

public class VisitedPlaceActivity extends AppCompatActivity {

    private RecyclerView placeRecycler;
    private VisitedPlaceViewModel mPlaceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        // Toolbar Override set
        String message = getIntent().getStringExtra(HolidayListAdapter.EXTRA_REPLY);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Back button enabled but not returning to home
        getSupportActionBar().setTitle("Holiday on: " + message); // Override Action Bar title

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

        // View Model Initialize
        mPlaceViewModel = ViewModelProviders.of(this).get(VisitedPlaceViewModel.class);
        mPlaceViewModel.getAllPlaces().observe(this, new Observer<List<VisitedPlace>>() {
            @Override
            public void onChanged(List<VisitedPlace> visitedPlaces) {
                placeAdapter.setPlaces(visitedPlaces);
            }
        });
    }
}
