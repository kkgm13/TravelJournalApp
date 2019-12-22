package com.kkgmdevelopments.traveljournalapp;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.holiday.HolidayListAdapter;
import com.kkgmdevelopments.traveljournalapp.holiday.HolidayViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabHolidayFragment extends Fragment {

    // Holiday View Model Instance
    private HolidayViewModel mHolidayViewModel;

    public TabHolidayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_holiday, container, false);

        // Present Data to RecyclerView
        RecyclerView recyclerView = recyclerView.findViewById(R.id.holiday_list);
        final HolidayListAdapter adapter = new HolidayListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create View Modal
        mHolidayViewModel = ViewModelProviders.of(this).get(HolidayViewModel.class);

        //Get all the holidays and observe changes
        mHolidayViewModel.getAllHolidays().observe(this, new Observer<List<Holiday>>() {
            /**
             * On Changed Method
             *  If a change is known, update it
             * @param holidays
             */
            @Override
            public void onChanged(@Nullable List<Holiday> holidays) {
            // Update cached copy in the adapter
            adapter.setHolidays(holidays);
            }
        });
    }

}
