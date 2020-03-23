package com.kkgmdevelopments.traveljournalapp.placeimage;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.kkgmdevelopments.traveljournalapp.JournalAppDatabase;
import java.util.List;

public class PlaceImageRepository {
    private PlaceImageDAO mDao;
    private MutableLiveData<List<PlaceImage>> placePictureList;

    public PlaceImageRepository(Application application){
        JournalAppDatabase db = JournalAppDatabase.getDatabase(application);
        mDao = db.placeImageDAO();
        placePictureList = new MutableLiveData<>();
    }

    public void insertImage(PlaceImage image){
        new insertPlaceImageAsyncTask(mDao).execute(image);
    }

    /**
     * Delete a image place based on the PlaceImage
     * @param image PlaceImage
     */
    public void deleteImage(PlaceImage image){
        new deleteImageAsyncTask(mDao).execute(image);
    }

    /**
     * Delet all the Images
     */
    public void deleteAllImages(){
        new deleteAllImagesAsyncTask(mDao).execute();
    }

    /**
     * Get All Images based on a selected Place
     * @param placeID VisitedPlace ID
     */
    public void getPlaceImages(int placeID){
        Log.i("TAG", "Getting pictures for "+placeID);
        new getPlaceImagesAsyncTask(mDao).execute(placeID);
    }

    /**
     * Get All Images from DB
     * @return
     */
    public LiveData<List<PlaceImage>> getAllImages(){
        return placePictureList;
    }

    /**
     * Get Images for Gallery
     */
    public void getPlaceAllImages(){
        new getPlaceAllImagesAsyncTask(mDao).execute();
    }

    /**
     * Async task to insert an image
     */
    private class insertPlaceImageAsyncTask extends AsyncTask<PlaceImage, Void, Void>{
        private PlaceImageDAO mDao;

        public insertPlaceImageAsyncTask(PlaceImageDAO mDao){
            this.mDao = mDao;
        }

        @Override
        protected Void doInBackground(final PlaceImage... params) {
            mDao.insertImage(params[0]);
            return null;
        }
    }

    /**
     * Async Task to delete an image
     */
    private class deleteImageAsyncTask extends AsyncTask<PlaceImage, Void, Void>{
        private PlaceImageDAO mDao;

        public deleteImageAsyncTask(PlaceImageDAO mDao){
            this.mDao = mDao;
        }

        @Override
        protected Void doInBackground(final PlaceImage... params) {
            mDao.deleteImage(params[0]);
            return null;
        }
    }

    /**
     * Async Task to delete all images
     */
    private class deleteAllImagesAsyncTask extends AsyncTask<Void, Void, Void>{
        private PlaceImageDAO mDao;

        public deleteAllImagesAsyncTask(PlaceImageDAO mDao){
            this.mDao = mDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAllImages();
            return null;
        }
    }

    /**
     * Async task to get all pictures from a selected Visited Place
     */
    private class getPlaceImagesAsyncTask extends AsyncTask<Integer, Void, List<PlaceImage>>{
        private PlaceImageDAO mDao;

        public getPlaceImagesAsyncTask(PlaceImageDAO mDao){
            this.mDao = mDao;
        }

        @Override
        protected List<PlaceImage> doInBackground(Integer... ids) {
            List<PlaceImage> pics = (mDao.getAllPlaceImages(ids[0]));
            return pics;
        }

        @Override
        protected void onPostExecute(List<PlaceImage> placeImages) {
            super.onPostExecute(placeImages);
            placePictureList.setValue(placeImages);
        }
    }

    /**
     * Async task to get all Visted place pictures from an associated Holiday
     */
    private class getPlaceAllImagesAsyncTask extends AsyncTask<Void, Void, List<PlaceImage>>{
        private PlaceImageDAO mDao;

        public getPlaceAllImagesAsyncTask(PlaceImageDAO mDao){
            this.mDao = mDao;
        }

        @Override
        protected List<PlaceImage> doInBackground(Void... voids) {
            List<PlaceImage> pics = (mDao.getAllImages());
            return pics;
        }

        @Override
        protected void onPostExecute(List<PlaceImage> placeImages) {
            super.onPostExecute(placeImages);
            placePictureList.setValue(placeImages);
        }
    }
}
