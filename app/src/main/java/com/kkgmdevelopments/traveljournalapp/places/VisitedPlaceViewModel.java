package com.kkgmdevelopments.traveljournalapp.places;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VisitedPlaceViewModel extends AndroidViewModel {

    private VisitedPlaceRepository mRepository;
    private LiveData<List<VisitedPlace>> mAllPlaces;

    public VisitedPlaceViewModel(@NonNull Application application) {
        super(application);
        mRepository = new VisitedPlaceRepository(application);
        mAllPlaces = mRepository.getEveryPlaces();
    }

    public LiveData<List<VisitedPlace>> getAllPlaces() {
        return mAllPlaces;
    }

    public void insert(VisitedPlace place){
        mRepository.insert(place);
    }
}
