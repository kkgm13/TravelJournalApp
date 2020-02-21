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

    public void deleteAll(){ new deleteALLPlacesAsyncTask(mPlaceDAO).execute();}

    public void deletePlace(VisitedPlace place) { new deletePlaceAsyncTask(mPlaceDAO).execute(place);}

    public void updatePlace(VisitedPlace place) { new updatePlaceAsyncTask(mPlaceDAO).execute(place);}

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

    /**
     * Delete all Async Tasks
     */
    private static class deleteALLPlacesAsyncTask extends AsyncTask<Void, Void, Void>{
        private VisitedPlaceDAO mAsyncTaskDao;

        deleteALLPlacesAsyncTask(VisitedPlaceDAO dao){
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * Delete a Place Asynchronously
     */
    private static class deletePlaceAsyncTask extends AsyncTask<VisitedPlace, Void, Void>{
        private VisitedPlaceDAO mAsyncDao;

        deletePlaceAsyncTask(VisitedPlaceDAO dao){
            this.mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(final VisitedPlace... params) {
            mAsyncDao.deletePlace(params[0]);
            return null;
        }
    }

    /**
     * Update a Place Asynchronously
     */
    private static class updatePlaceAsyncTask extends AsyncTask<VisitedPlace, Void, Void>{
        private VisitedPlaceDAO mAsyncDao;

        updatePlaceAsyncTask(VisitedPlaceDAO dao){
            this.mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(final VisitedPlace... params) {
            mAsyncDao.updatePlace(params[0]);
            return null;
        }
    }

}
