package com.kkgmdevelopments.traveljournalapp.holiday;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.kkgmdevelopments.traveljournalapp.holidayplaces.HolidayPlace;

import java.util.List;

/**
 * Holiday DAO (Data Access Object)
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
     * Get any Holiday
     */
    @Query("SELECT * FROM holiday_table LIMIT 1")
//    LiveData<List<Holiday>> getAnyHoliday();
    Holiday[] getAnyHoliday();

    /**
     * Get all records from the Live DB
     */
    @Query("SELECT * FROM holiday_table ORDER BY holidayName ASC")
    LiveData<List<Holiday>> getAllHolidays();

    /**
     * Delete a Holiday
     * @param holiday Selected Holiday
     */
    @Delete
    void deleteHoliday(Holiday holiday);

    /**
     * Update a Holiday
     * @param holiday Selected Holiday
     */
    @Update
    void updateHoliday(Holiday... holiday);
}
