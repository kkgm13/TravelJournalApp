package com.kkgmdevelopments.traveljournalapp.places;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Visited Place DAO
 *  This is the Room Database handlers for a Visited Place.
 *  Room is the Database handler for Android
 */
@Dao
public interface VisitedPlaceDAO {

    @Insert
    void insert(VisitedPlace place);

//    void delete(VisitedPlace place);

    @Query("DELETE From places_table")
    void deleteAll();

    @Query("SELECT * FROM places_table ORDER BY placeName ASC")
    LiveData<List<VisitedPlace>> getAllPlaces();
}
