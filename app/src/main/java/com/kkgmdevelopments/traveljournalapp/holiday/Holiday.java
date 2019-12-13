package com.kkgmdevelopments.traveljournalapp.holiday;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//import java.util.Date;

/**
 * Holiday Model
 * Bitmap = Images???
 */
@Entity(tableName = "holiday_table")
public class Holiday implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mHolidayID;

    @NonNull
    @ColumnInfo(name = "holidayName")
    private String mHolidayName;

    @NonNull
    @ColumnInfo(name = "holidayStart")
    private int mStartDate; // https://developer.android.com/training/data-storage/room/referencing-data#java

    @NonNull
    @ColumnInfo(name = "holidayEnd")
    private int mEndDate; // https://developer.android.com/training/data-storage/room/referencing-data#java

    @ColumnInfo(name = "holidayNotes")
    private String mHolidayNotes;

    @Ignore
    public Holiday(){

    }

//int mStartDate, int mEndDate,
    public Holiday(String mHolidayName, String mHolidayNotes){
        this.mHolidayName = mHolidayName;
//        this.mStartDate = mStartDate;
//        this.mEndDate = mEndDate;
        this.mHolidayNotes = mHolidayNotes;
    }

    public int getMHolidayID() {
        return mHolidayID;
    }

    public void setMHolidayID(int mHolidayID) {
        this.mHolidayID = mHolidayID;
    }

    @NonNull
    public String getMHolidayName() {
        return mHolidayName;
    }

    public void setMHolidayName(@NonNull String mHolidayName) {
        this.mHolidayName = mHolidayName;
    }

    public int getMStartDate() {
        return mStartDate;
    }

    public void setMStartDate(int mStartDate) {
        this.mStartDate = mStartDate;
    }

    public int getMEndDate() {
        return mEndDate;
    }

    public void setMEndDate(int mEndDate) {
        this.mEndDate = mEndDate;
    }

    public String getMHolidayNotes() {
        return mHolidayNotes;
    }

    public void setMHolidayNotes(String mHolidayNotes) {
        this.mHolidayNotes = mHolidayNotes;
    }
}
