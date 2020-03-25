package com.kkgmdevelopments.traveljournalapp;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;

/**
 * Fragment Pager Adapter
 *  This is the core Adapter to utilize a Tabbed Fragment within
 *  the VisitedPlaces Section
 */
public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    Context context;    // Context
    int numOfTabs;      // Number of Pager Tabs
    Holiday holiday;    // Holiday Object

    /**
     * Constructor
     * @param fm
     * @param numOfTabs
     */
    public FragmentPagerAdapter(FragmentManager fm, Context context, int numOfTabs, Holiday holiday) {
        super(fm); // Deprecated Issues
        this.numOfTabs = numOfTabs;
        this.context = context;
        this.holiday = holiday;
    }

    /**
     * Get Fragment Item selected position
     *
     * @param position
     * @return All tabs and Fragment issues
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            // Todo: Fix Gallery with the full Image view selection
            case 0: return new TabGalleryFragment();
            case 1: return TabPlacesFragment.newInstance(holiday);
            case 2: return new TabExploreFragment();
            default: return null;
        }
    }

    /**
     * Get available number of views
     *
     * @return Number of Views
     */
    @Override
    public int getCount() {
        return numOfTabs;
    }
}
