package com.kkgmdevelopments.traveljournalapp.holiday;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.kkgmdevelopments.traveljournalapp.JournalAppDatabase;

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
    public HolidayRepository(Application application){
        JournalAppDatabase db = JournalAppDatabase.getDatabase(application);
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
     * Update a Holiday
     *
     * @param holiday
     */
    public void updateHoliday(Holiday holiday){
        new updateHolidayAsyncTask(mHolidayDao).execute(holiday);
    }

    /**
     * Delete a Holiday
     *
     * @param holiday Selected Holiday
     */
    public void deleteHoliday(Holiday holiday){
        new deleteHolidayAsyncTask(mHolidayDao).execute(holiday);
    }

    /**
     * Delete All Holiday Records
     */
    public void deleteAll(){new deleteAllHolidayAsyncTask(mHolidayDao).execute();}

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

    /**
     * Allow to Asynchronous delete the records
     */
    private static class deleteAllHolidayAsyncTask extends AsyncTask<Void, Void, Void> {
        private HolidayDAO mAsyncTaskDao;

        public deleteAllHolidayAsyncTask(HolidayDAO dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteALL();
            return null;
        }
    }

    /**
     * Allow to Asynchronous delete a holiday
     */
    private static class deleteHolidayAsyncTask extends AsyncTask<Holiday, Void, Void>{
        private HolidayDAO mAsyncTaskDao;

        public deleteHolidayAsyncTask(HolidayDAO dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Holiday... params) {
            mAsyncTaskDao.deleteHoliday(params[0]);
            return null;
        }
    }

    /**
     * Allow to Asynchronous update a holiday
     */
    private static class updateHolidayAsyncTask extends AsyncTask<Holiday, Void, Void>{
        private HolidayDAO mAsyncTaskDao;

        public updateHolidayAsyncTask(HolidayDAO dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Holiday... params) {
            mAsyncTaskDao.updateHoliday(params[0]);
            return null;
        }
    }
}