package com.kkgmdevelopments.traveljournalapp.placeimage;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

/**
 * PlaceImage View Model
 *  This is the View Model for the PlaceImage to call repository tasks
 */
public class PlaceImageViewModel extends AndroidViewModel {
    private PlaceImageRepository mRepository;   // Repo Instance

    /**
     * Constructor
     * @param application
     */
    public PlaceImageViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PlaceImageRepository(application);
    }

    /**
     * Insert a PlaceImage
     * @param image
     */
    public void insertImage(PlaceImage image){
        mRepository.insertPlaceImage(image);
    }

    /**
     * Delete a PlaceImage
     * @param image
     */
    public void deleteImage(PlaceImage image){
        mRepository.deletePlaceImage(image);
    }

    /**
     * Delete all PlaceImage
     */
    public void deleteAllImages(){
        mRepository.deleteAllPlaceImages();;
    }

    /**
     * Get the PlaceImage based on a specific ID
     * @param placeID VisitedPlace ID
     */
    public void getPlaceImages(int placeID){
        mRepository.getPlaceImages(placeID);
    }

    /**
     * Get all the PlaceImage as a list
     * @return LiveData list of PlaceImage
     */
    public LiveData<List<PlaceImage>> getAllImages(){
        return mRepository.getAllImages();
    }

    /**
     * Get all the Places based on the selected place
     */
    public void getPlaceAllImages(){
        mRepository.getPlaceAllImages();
    }
}
