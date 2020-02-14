package com.kkgmdevelopments.traveljournalapp;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    Context context;
    int numOfTabs; // Number of Pager Tabs
    Holiday holiday;

    /**
     * Constructor
     * @param fm
     * @param numOfTabs
     */
    public FragmentPagerAdapter(FragmentManager fm, Context context, int numOfTabs
                                , Holiday holiday
    ) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.context = context;
        this.holiday = holiday;
    }

    /**
     * Get Fragment Item selected position
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return TabPlacesFragment.newInstance(holiday);
            case 1: return new TabExploreFragment();
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
