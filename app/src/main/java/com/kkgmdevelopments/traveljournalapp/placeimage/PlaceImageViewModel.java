package com.kkgmdevelopments.traveljournalapp.placeimage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PlaceImageViewModel extends AndroidViewModel {
    private PlaceImageRepository mRepository;

    public PlaceImageViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PlaceImageRepository(application);
    }

    public void insertImage(PlaceImage image){
        mRepository.insertImage(image);
    }

    public void deleteImage(PlaceImage image){
        mRepository.deleteImage(image);
    }

    public void deleteAllImages(){
        mRepository.deleteAllImages();;
    }

    public void getPlaceImages(int placeID){
        mRepository.getPlaceImages(placeID);
    }

    public LiveData<List<PlaceImage>> getAllImages(){
        return mRepository.getAllImages();
    }
}
