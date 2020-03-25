package com.kkgmdevelopments.traveljournalapp.places;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.kkgmdevelopments.traveljournalapp.holidayplaces.HolidayPlaces;

import java.util.List;

/**
 * Visited Place DAO
 *  This is the Room Database handlers for a Visited Place.
 *  Room is the Database handler for Android
 */
@Dao
public interface VisitedPlaceDAO {

    /**
     * Insert a Visited Place
     * @param place Visited Place Object
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(VisitedPlace place);

    /**
     * Delete ALL Visited Places
     */
    @Query("DELETE From places_table")
    void deleteAll();

    /**
     * Get all the Places to present
     * @return Live Data list of Visited Places
     */
    @Query("SELECT * FROM places_table ORDER BY placeDate DESC")
    LiveData<List<VisitedPlace>> getAllPlaces();

    /**
     * Delete a Specific Visited Place
     * @param place Visited Place Object
     */
    @Delete
    void deletePlace(VisitedPlace place);

    /**
     * Updated Place
      * @param place
     */
    @Update
    void updatePlace(VisitedPlace... place);

    /**
     * Get visisted places based on Specific Holiday
     *  NOT IN USE
     * @return List of Visited Places based on Holiday
     */
//    @Transaction
//    @Query("SELECT * FROM holiday_table WHERE hID IN (SELECT DISTINCT(holidayID) FROM places_table)")
//    LiveData<List<HolidayPlaces>> getVisitedPlaces();
}
