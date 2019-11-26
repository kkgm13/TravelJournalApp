package com.kkgmdevelopments.traveljournalapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HolidayViewModal extends AndroidViewModel {

    private HolidayRepository mRepository;

    private LiveData<List<Holiday>> mAllHolidays;

    public HolidayViewModal(Application application) {
        super(application);
        mRepository = new HolidayRepository(application);
        mAllHolidays = mRepository.getAllHolidays();
    }

    /**
     * Get all the Holidays
     * @return List of Holidays
     */
    public LiveData<List<Holiday>> getAllHolidays() {
        return mAllHolidays;
    }

    /**
     * Insert a Holiday
     *
     * @param holiday
     */
    public void insert(Holiday holiday){
        mRepository.insert(holiday);
    }


}
