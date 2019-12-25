package com.kkgmdevelopments.traveljournalapp.places;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;

/**
 * Holiday Repository
 *
 * Controls all the holidays and DB connection instance
 */
public class VisitedPlaceRepository {
    public VisitedPlaceDAO mPlaceDAO;
    public LiveData<List<VisitedPlace>> mAllPlaces;

    /**
     * Constructor
     * @param application Current Application instance
     */
    VisitedPlaceRepository(Application application){
        VisitedPlaceRoomDatabase db =  VisitedPlaceRoomDatabase.getDatabase(application);
        mPlaceDAO = db.placeDAO();
        mAllPlaces = mPlaceDAO.getAllPlaces();
    }

    /**
     * Get All Visited Places from the Database
     * @return LiveData List of Visited Places
     */
    LiveData<List<VisitedPlace>> getEveryPlaces(){
        return mAllPlaces;
    }

    /**
     * Insert a new Visited Place Asynchronously
     * @param place Visited Place
     */
    public void insert(VisitedPlace place){
        new insertAsyncTask(mPlaceDAO).execute(place);
    }

    /**
     * Allow to insert an Asynchronous Task
     */
    private static class insertAsyncTask extends AsyncTask<VisitedPlace, Void, Void>{

        public VisitedPlaceDAO mAsyncTaskDao;

        insertAsyncTask(VisitedPlaceDAO placeDAO){
            mAsyncTaskDao = placeDAO;
        }

        @Override
        protected Void doInBackground(final VisitedPlace... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
