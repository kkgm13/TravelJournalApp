package com.kkgmdevelopments.traveljournalapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Holiday.class}, version = 2, exportSchema = false)
public abstract class HolidayRoomDatabase extends RoomDatabase {

    // Singleton instance of the Database
    private static HolidayRoomDatabase INSTANCE;

    // Abstract DAO getter
    public abstract HolidayDAO holidayDAO();

    static HolidayRoomDatabase getDatabase(final Context context){
        // If the instance has nothing
        if(INSTANCE == null){
            // Synch the Database
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
                            .addCallback(sRoomDataCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDataCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final HolidayDAO hDao;
        String [] holidays = { "London", "Birmingham", "Brighton"};

        PopulateDbAsync(HolidayRoomDatabase db) {
            hDao = db.holidayDAO();
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            hDao.deleteALL();
            for(int i = 0; i < holidays.length; i++){
                Holiday holiday = new Holiday(holidays[i]);
                hDao.insert(holiday);
            }
            return null;
        }
    }

}
