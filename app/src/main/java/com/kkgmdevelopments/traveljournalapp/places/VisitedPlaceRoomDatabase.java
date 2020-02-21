package com.kkgmdevelopments.traveljournalapp.places;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.kkgmdevelopments.traveljournalapp.DateConverter;

/**
 * Holiday Database
 * This creates the database for Android Room to recognise.
 *
 */
@Database(entities = {VisitedPlace.class}, version = 3, exportSchema = false)
@TypeConverters({DateConverter.class})
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
//            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate Dummy Data to the DB
     */
    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void>{

        private final VisitedPlaceDAO vpDao;
//        String[] visitedPlaces = { "Place 1", "Place 2", "Place 3"};
//        private LiveData<List<VisitedPlace>> visitedPlaces;

        PopulateDBAsync(VisitedPlaceRoomDatabase db){
            vpDao = db.placeDAO();
//            visitedPlaces = vpDao.getAllPlaces();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            vpDao.deleteAll();
            vpDao.getAllPlaces();

//            for(int i = 0; i < visitedPlaces.length; i++){
//                VisitedPlace vp = new VisitedPlace(visitedPlaces[i]);
//                vpDao.insert(vp);
//            }

            return null;
        }
    }
}
