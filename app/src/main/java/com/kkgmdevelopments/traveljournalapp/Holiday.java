package com.kkgmdevelopments.traveljournalapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Holiday Model
 */
@Entity(tableName = "holiday_table")
public class Holiday {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "holidayName")
    private String mHolidayName;

    public Holiday(@NonNull String mHolidayName){
        this.mHolidayName = mHolidayName;
    }

    public String getMHolidayName() {
        return this.mHolidayName;
    }
}
