package com.kkgmdevelopments.traveljournalapp.holiday;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Holiday Repository
 *
 * Controls all the holidays and DB connection instance
 */
public class HolidayRepository {

    private HolidayDAO mHolidayDao; // DB Holiday Model
    private LiveData<List<Holiday>> mAllHolidays; // DB Data of all holidays

    /**
     * Holiday Repository Constructor
     *
     * @param application
     */
    HolidayRepository(Application application){
        HolidayRoomDatabase db = HolidayRoomDatabase.getDatabase(application);
        mHolidayDao = db.holidayDAO();
        mAllHolidays = mHolidayDao.getAllHolidays();
    }

    /**
     * Get All Holidays
     *
     * @return All holiday Records
     */
    LiveData<List<Holiday>> getAllHolidays(){
        return mAllHolidays;
    }


    /**
     * Insert a New Holiday
     *
     * @param holiday
     */
    public void insert (Holiday holiday){
        new insertAsyncTask(mHolidayDao).execute(holiday);
    }

    /**
     * Allow to insert an Asynchronous Task
     */
    private static class insertAsyncTask extends AsyncTask<Holiday, Void, Void>{
        private HolidayDAO mAsyncTaskDao;

        /**
         * Insert an Asynchronous Task
         * @param mHolidayDao
         */
        public insertAsyncTask(HolidayDAO mHolidayDao) {
            mAsyncTaskDao = mHolidayDao;
        }

        /**
         * Background Data
         *
         * @param params Holiday Parameters
         * @return null
         */
        @Override
        protected Void doInBackground(final Holiday... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
