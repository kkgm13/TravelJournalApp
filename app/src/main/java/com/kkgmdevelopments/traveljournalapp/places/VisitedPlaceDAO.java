package com.kkgmdevelopments.traveljournalapp.places;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.kkgmdevelopments.traveljournalapp.holidayplaces.HolidayPlace;

import java.util.List;

/**
 * Visited Place DAO
 *  This is the Room Database handlers for a Visited Place.
 *  Room is the Database handler for Android
 */
@Dao
public interface VisitedPlaceDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(VisitedPlace place);

//    void delete(VisitedPlace place);

    @Query("DELETE From places_table")
    void deleteAll();

    @Query("SELECT * FROM places_table ORDER BY placeName ASC")
    LiveData<List<VisitedPlace>> getAllPlaces();

//    @Transaction
//    @Query("SELECT * FROM holiday_table")
//    List<HolidayPlace> getHolidaywithPlaces();
    // error: There is a problem with the query: [SQLITE_ERROR] SQL error or missing database (no such table: holiday_table)

}
