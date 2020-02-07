package com.kkgmdevelopments.traveljournalapp.holiday;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.kkgmdevelopments.traveljournalapp.holidayplaces.HolidayPlace;

import java.util.List;

/**
 * Holiday DAO
 *  This is the Room Database handlers for a Holiday.
 *  Room is the Middleman for the DB and Android
 */
@Dao
public interface HolidayDAO {
    /**
     * Insert a new Holiday to the DB
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Holiday holiday);

    /**
     *  Delete all records from DB
     */
    @Query("DELETE FROM holiday_table")
    void deleteALL();

    /**
     * Get all records from the Live DB
     */
    @Query("SELECT * FROM holiday_table ORDER BY holidayName ASC")
    LiveData<List<Holiday>> getAllHolidays();
}
