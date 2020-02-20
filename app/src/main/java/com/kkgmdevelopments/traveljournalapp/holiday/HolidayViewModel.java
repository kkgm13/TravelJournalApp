package com.kkgmdevelopments.traveljournalapp.holiday;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

/**
 * The HolidayViewModel provides the interface between the UI and the data layer
 * of the app, represented by the Repository
 */
public class HolidayViewModel extends AndroidViewModel {

    private HolidayRepository mRepository;
    private LiveData<List<Holiday>> mAllHolidays;

    public HolidayViewModel(Application application) {
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

    /**
     * Delete ALL holidays
     */
    public void deleteAll() { mRepository.deleteAll();}

    /**
     * Delete a Holiday
     *
     * @param holiday Selected Holiday
     */
    public void deleteHoliday(Holiday holiday) {
        mRepository.deleteHoliday(holiday);
    }

    /**
     * Update a Holiday
     *
     * @param holiday Selected Holiday
     */
    public void updateHoliday(Holiday holiday) {
        mRepository.updateHoliday(holiday);
    }
}