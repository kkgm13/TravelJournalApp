package com.kkgmdevelopments.traveljournalapp.holiday;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// Provides a way to interact with Holiday Objects via Intents
import java.io.Serializable;
import java.sql.Date;

/**
 * Holiday Model
 * Bitmap = Images???
 */
@Entity(tableName = "holiday_table")
public class Holiday implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "hID")
    private int mHolidayID;

    @NonNull
    @ColumnInfo(name = "holidayName")
    private String mHolidayName;
//
//    @NonNull
//    @ColumnInfo(name = "holidayStart")
//    private Date mStartDate; // https://developer.android.com/training/data-storage/room/referencing-data#java

//    @NonNull
//    @ColumnInfo(name = "holidayEnd")
//    private Date mEndDate; // https://developer.android.com/training/data-storage/room/referencing-data#java

    @ColumnInfo(name = "holidayNotes")
    private String mHolidayNotes;

//    @ColumnInfo(name = "created_at")
//    private Calendar mHolidayCreatedAt = Calendar.getInstance();
//
//    @ColumnInfo(name = "modified_at")
//    private Calendar mHolidayModifiedAt = Calendar.getInstance();

    @Ignore
    public Holiday(){

    }


    // Constructor
    public Holiday(
//            int mHolidayID,
            String mHolidayName, String mHolidayNotes){
//        this.mHolidayID = mHolidayID;
        this.mHolidayName = mHolidayName;
//        this.mStartDate = mStartDate;
//        this.mEndDate = mEndDate;
        this.mHolidayNotes = mHolidayNotes;
    }

    // Encapsulation Methods //

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

//    public Date getMStartDate() {
//        return mStartDate;
//    }

//    public void setMStartDate(Date mStartDate) {
//        this.mStartDate = mStartDate;
//    }
//
//    public Date getMEndDate() {
//        return mEndDate;
//    }
//
//    public void setMEndDate(Date mEndDate) {
//        this.mEndDate = mEndDate;
//    }

    public String getMHolidayNotes() {
        return mHolidayNotes;
    }

    public void setMHolidayNotes(String mHolidayNotes) {
        this.mHolidayNotes = mHolidayNotes;
    }
}
