package com.kkgmdevelopments.traveljournalapp.holiday;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.kkgmdevelopments.traveljournalapp.DateConverter;

import java.util.List;

/**
 * Holiday Database
 *
 * This creates the database for Android Room to recognise.
 * This also talks to HolidayViewModel to interact with data.
 */
@Database(entities = {Holiday.class}, version = 7, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class HolidayRoomDatabase extends RoomDatabase {
    // Singleton instance of the Database
    private static HolidayRoomDatabase INSTANCE;
    // Abstract DAO
    public abstract HolidayDAO holidayDAO();

    /**
     * Get the Database
     * @param context
     * @return Room Database Instance
     */
    public static HolidayRoomDatabase getDatabase(final Context context){
        // If the instance has nothing
        if(INSTANCE == null){
            // Sync the Database
            synchronized (HolidayRoomDatabase.class){
                // If there is no database
                if(INSTANCE == null){
                    // Create the database
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),    // Context from the Application
                            HolidayRoomDatabase.class,          // The Room Database
                            "holiday_database"           // The Database Name (App or Model?)
                    )
                            .fallbackToDestructiveMigration()   // Wipes & rebuilds if no migration object
                            .addCallback(sRoomDataCallback)     // Provide a Callback to the Room Database
                            .build();                           // Build the Database
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Room Callback to the Room Database
     */
    private static RoomDatabase.Callback sRoomDataCallback = new RoomDatabase.Callback(){

        /**
         * On Open Callback
         * @param db Database
         */
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate Dummy Data to the DB
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final HolidayDAO hDao;
//        String [] holidays = { "London", "Birmingham", "Brighton"};
//        private LiveData<List<Holiday>> holidays;

        PopulateDbAsync(HolidayRoomDatabase db) {
            hDao = db.holidayDAO();
//            holidays = hDao.getAllHolidays();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // First Time use and cleaning
//            hDao.deleteALL();
            hDao.getAllHolidays();

//            if(hDao.getAnyHoliday().length < 1){
//
//            }

//            for(int i = 0; i < holidays.length; i++){
//                Holiday holiday = new Holiday(holidays[i]);
//                hDao.insert(holiday);
//            }
            return null;
        }
    }
}
