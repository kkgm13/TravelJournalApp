package com.kkgmdevelopments.traveljournalapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs; // Number of Pager Tabs

    /**
     * Constructor
     * @param fm
     * @param numOfTabs
     */
    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
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
            case 0: return null;
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
