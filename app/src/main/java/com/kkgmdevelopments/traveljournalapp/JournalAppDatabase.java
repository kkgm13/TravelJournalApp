package com.kkgmdevelopments.traveljournalapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.holiday.HolidayDAO;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImage;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImageDAO;
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlace;
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlaceDAO;

/**
 * Room Database
 *
 * This creates the Overall database for Android Room to recognise.
 * This also talks to ANY Data Access Object (DAOs) to interact with data.
 */
@Database(entities = {Holiday.class, VisitedPlace.class, PlaceImage.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class JournalAppDatabase extends RoomDatabase {
    // Singleton instance of the Database
    private static JournalAppDatabase INSTANCE;
    // Abstract DAO Holidays
    public abstract HolidayDAO holidayDAO();
    // Abstract DAO Visited Places
    public abstract VisitedPlaceDAO placeDAO();
    // Abstract DAO Place Images
    public abstract PlaceImageDAO placeImageDAO();

    /**
     * Get the Database
     * @param context
     * @return Room Database Instance
     */
    public static JournalAppDatabase getDatabase(final Context context){
        // If the instance has nothing
        if(INSTANCE == null){
            // Sync the Database
            synchronized (JournalAppDatabase.class){
                // If there is no database
                if(INSTANCE == null){
                    // Create the database
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),    // Context from the Application
                            JournalAppDatabase.class,          // The Room Database
                            "traveljournalapp.db"  // The Database Name (App)
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
        private final VisitedPlaceDAO pDAO;
        private final PlaceImageDAO piDAO;
//        String [] holidays = { "London", "Birmingham", "Brighton"};
//        private LiveData<List<Holiday>> holidays;

        PopulateDbAsync(JournalAppDatabase db) {
            hDao = db.holidayDAO();
            pDAO = db.placeDAO();
            piDAO = db.placeImageDAO();

//            holidays = hDao.getAllHolidays();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // First Time use and cleaning
//            hDao.deleteALL();
            hDao.getAllHolidays();
            pDAO.getAllPlaces();
            piDAO.getAllImages();

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
