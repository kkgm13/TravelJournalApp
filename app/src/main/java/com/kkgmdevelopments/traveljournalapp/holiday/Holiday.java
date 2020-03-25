package com.kkgmdevelopments.traveljournalapp.holiday;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import java.util.Date;

/**
 * Holiday Model
 *  This is the model to represent a single Holiday
 *  This implements the
 */
@Entity(tableName = "holiday_table")
public class Holiday implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "hID")
    private int mHolidayID;

    @NonNull
    @ColumnInfo(name = "holidayName")
    private String mHolidayName;

    @NonNull
    @ColumnInfo(name = "holidayStart")
    private Date mStartDate;

    @ColumnInfo(name = "companions")
    private String mCompanions;

    @NonNull
    @ColumnInfo(name = "holidayEnd")
    private Date mEndDate; // https://developer.android.com/training/data-storage/room/referencing-data#java

    @ColumnInfo(name = "holidayNotes")
    private String mHolidayNotes;

    @ColumnInfo(name = "created_at")
    private Date mHolidayCreatedAt;

    @ColumnInfo(name = "modified_at")
    private Date mHolidayModifiedAt;

    @Ignore
    public Holiday(){

    }

    @Ignore
    public Holiday(int id, @NonNull String mHolidayName, Date mStartDate, Date mEndDate,  String mCompanions, String mHolidayNotes, Date mHolidayCreatedAt, Date mHolidayModifiedAt){
        this.mHolidayID = id;
        this.mHolidayName = mHolidayName;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mCompanions = mCompanions;
        this.mHolidayNotes = mHolidayNotes;
        this.mHolidayCreatedAt = mHolidayCreatedAt;
        this.mHolidayModifiedAt = mHolidayModifiedAt;
    }

    /**
     * Constructor
     * @param mHolidayName  Holiday Name
     * @param mStartDate    Holiday Start date
     * @param mEndDate      Holiday End Date
     * @param mCompanions   Holiday Companions
     * @param mHolidayNotes Holiday End Notes
     * @param mHolidayCreatedAt     Holiday Creation Date
     * @param mHolidayModifiedAt    Holiday Modified Date
     */
    public Holiday(String mHolidayName, Date mStartDate, Date mEndDate, String mCompanions, String mHolidayNotes, Date mHolidayCreatedAt, Date mHolidayModifiedAt){
        this.mHolidayName = mHolidayName;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mCompanions = mCompanions;
        this.mHolidayNotes = mHolidayNotes;
        this.mHolidayCreatedAt = mHolidayCreatedAt;
        this.mHolidayModifiedAt = mHolidayModifiedAt;
    }

    // Encapsulation Methods //
    public int getMHolidayID() {
        return mHolidayID;
    }
    public void setMHolidayID(int mHolidayID) {
        this.mHolidayID = mHolidayID;
    }

    public String getMHolidayName() {
        return mHolidayName;
    }
    public void setMHolidayName(@NonNull String mHolidayName) {
        this.mHolidayName = mHolidayName;
    }

    public Date getMStartDate() {
        return mStartDate;
    }
    public void setMStartDate(Date mStartDate) {
        this.mStartDate = mStartDate;
    }

    public Date getMEndDate() {
        return mEndDate;
    }
    public void setMEndDate(Date mEndDate) {
        this.mEndDate = mEndDate;
    }

    public String getMHolidayNotes() {
        return mHolidayNotes;
    }
    public void setMHolidayNotes(String mHolidayNotes) {
        this.mHolidayNotes = mHolidayNotes;
    }

    public Date getMHolidayCreatedAt() {
        return mHolidayCreatedAt;
    }
    public void setMHolidayCreatedAt(Date mHolidayCreatedAt) {
        this.mHolidayCreatedAt = mHolidayCreatedAt;
    }

    public Date getMHolidayModifiedAt() {
        return mHolidayModifiedAt;
    }
    public void setMHolidayModifiedAt(Date mHolidayModifiedAt) {
        this.mHolidayModifiedAt = mHolidayModifiedAt;
    }

    public String getMCompanions() {
        return mCompanions;
    }
    public void setMCompanions(String mCompanions) {
        this.mCompanions = mCompanions;
    }
}
