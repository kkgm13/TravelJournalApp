package com.kkgmdevelopments.traveljournalapp.places;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Holiday Database
 * This creates the database for Android Room to recognise.
 *
 */
@Database(entities = {VisitedPlace.class}, version = 1, exportSchema = false)
public abstract class VisitedPlaceRoomDatabase extends RoomDatabase {

    // Singleton instance of the Database
    private static VisitedPlaceRoomDatabase INSTANCE;
    // Abstract DAO getter
    public abstract VisitedPlaceDAO placeDAO();

    static VisitedPlaceRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (VisitedPlaceRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VisitedPlaceRoomDatabase.class, "places_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

}
