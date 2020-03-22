package com.kkgmdevelopments.traveljournalapp.places;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.kkgmdevelopments.traveljournalapp.FragmentPagerAdapter;
import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.SettingsActivity;
import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.holiday.HolidayListAdapter;

public class VisitedPlaceActivity extends AppCompatActivity {
    private Holiday holiday;    // Holiday Data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        // Toolbar Override set
        holiday = (Holiday) getIntent().getSerializableExtra("holiday");
        getSupportActionBar().setTitle(getIntent().getStringExtra(HolidayListAdapter.EXTRA_REPLY) + " Holiday"); // Override Action Bar title

        // Tabs Creation
        TabLayout tabLayout = findViewById(R.id.tab_list);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.gallery_tab_text));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.places_tab_text));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.explore_tab_text));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Pager Adapters Initialize
        final ViewPager viewPager = findViewById(R.id.places_pager);
        final FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext(), tabLayout.getTabCount(), holiday);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1); // Set the Default Tab Item to view
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
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_place, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Settings Option Item
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        // Settings Option Item
        if (id == R.id.share_setting) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Have a look in my Holiday to "+holiday.getMHolidayName());
            //sendIntent.setData();
            sendIntent.setType("text/plain");

            startActivity(Intent.createChooser(sendIntent, "Share "+holiday.getMHolidayName()+" Holiday to..."));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
