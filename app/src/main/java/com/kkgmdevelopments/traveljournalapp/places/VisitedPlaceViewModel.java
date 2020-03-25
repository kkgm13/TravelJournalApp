package com.kkgmdevelopments.traveljournalapp.places;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Visited Place View Model
 *  Interface between the UI and the data layer of the app,
 *  represented by the Repository
 */
public class VisitedPlaceViewModel extends AndroidViewModel {

    private VisitedPlaceRepository mRepository;         // Repository Instance
    private LiveData<List<VisitedPlace>> mAllPlaces;    // Live Data List of VisitedPlaces

    /**
     * Constructor
     * @param application
     */
    public VisitedPlaceViewModel(@NonNull Application application) {
        super(application);
        mRepository = new VisitedPlaceRepository(application);
        mAllPlaces = mRepository.getEveryPlaces();
    }

    /**
     * Get all Visited Places
     * @return LiveData list of VisitedPlaces
     */
    public LiveData<List<VisitedPlace>> getAllPlaces() {
        return mAllPlaces;
    }

    /**
     * Insert a VisitedPlace
     * @param place VisitedPlace Object
     */
    public void insert(VisitedPlace place){
        mRepository.insert(place);
    }

    /**
     * Delete All VisitedPlace Records
     */
    public void deleteAll(){
        mRepository.deleteAll();
    }

    /**
     * Delete a Specific Visited Place
     * @param place Visited Place Object
     */
    public void deletePlace(VisitedPlace place){
        mRepository.deletePlace(place);
    }

    /**
     * Update a Specific Visited Place
     * @param place VisitedPlace Object
     */
    public void updatePlace(VisitedPlace place){
        mRepository.updatePlace(place);
    }
}
