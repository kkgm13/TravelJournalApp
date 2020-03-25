package com.kkgmdevelopments.traveljournalapp.places;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

/**
 * Visited Place Model
 *
 * All associated with Places visited in the Holiday
 */
@Entity(tableName = "places_table")
public class VisitedPlace implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pID")
    private int mPlaceID;

    @NonNull
    @ColumnInfo(name = "holidayID")
    private int associatedHolidayID;

    @NonNull
    @ColumnInfo(name = "placeName")
    private String mPlaceName;

    @NonNull
    @ColumnInfo(name = "placeDate")
    private Date mPlaceDate;

    @Nullable
    @ColumnInfo(name = "placeLocation")
    private String mPlaceLocation;

    @ColumnInfo(name = "placeNotes")
    private String mPlaceNotes;

    @ColumnInfo(name = "created_at")
    private Date mPlaceCreatedAt;

    @ColumnInfo(name = "modified_at")
    private Date mPlaceModifiedAt;

    @Ignore
    public VisitedPlace(int id, String mPlaceName, String mPlaceLocation,
//                        int associatedHolidayID,
                        Date mPlaceDate, String mPlaceNotes, Date mPlaceCreatedAt, Date mPlaceModifiedAt){
        this.mPlaceID = id;
        this.mPlaceName = mPlaceName;
        this.mPlaceLocation = mPlaceLocation;
//        this.associatedHolidayID = associatedHolidayID;
        this.mPlaceDate = mPlaceDate;
        this.mPlaceNotes = mPlaceNotes;
        this.mPlaceCreatedAt = mPlaceCreatedAt;
        this.mPlaceModifiedAt = mPlaceModifiedAt;
    }

    /**
     * Constructor
     * @param mPlaceName        Visited Place Custom Name
     * @param mPlaceLocation    Visited Place Location
     * @param mPlaceDate        Visited Place Date of Visit
     * @param mPlaceNotes       Visited Place Notes
     * @param mPlaceCreatedAt
     * @param mPlaceModifiedAt
     */
    public VisitedPlace(String mPlaceName, String mPlaceLocation,
//                        int associatedHolidayID,
                        Date mPlaceDate, String mPlaceNotes, Date mPlaceCreatedAt, Date mPlaceModifiedAt){
        this.mPlaceName = mPlaceName;
        this.mPlaceDate = mPlaceDate;
        this.mPlaceLocation = mPlaceLocation;
//        this.associatedHolidayID = associatedHolidayID;
        this.mPlaceNotes = mPlaceNotes;
        this.mPlaceCreatedAt = mPlaceCreatedAt;
        this.mPlaceModifiedAt = mPlaceModifiedAt;
    }

    // Encapsulation Methods //
    public int getPlaceID() {
        return mPlaceID;
    }
    public void setPlaceID(int mPlaceID) {
        this.mPlaceID = mPlaceID;
    }

    public String getPlaceName() {
        return mPlaceName;
    }
    public void setPlaceName(String mPlaceName) {
        this.mPlaceName = mPlaceName;
    }

    public int getAssociatedHolidayID() {
        return associatedHolidayID;
    }
    public void setAssociatedHolidayID(int associatedHolidayID) {
        this.associatedHolidayID = associatedHolidayID;
    }

    public String getPlaceLocation() {
        return mPlaceLocation;
    }
    public void setPlaceLocation(String mPlaceLocation) {
        this.mPlaceLocation = mPlaceLocation;
    }

    public String getPlaceNotes() {
        return mPlaceNotes;
    }
    public void setPlaceNotes(String mPlaceNotes) {
        this.mPlaceNotes = mPlaceNotes;
    }

    public Date getPlaceCreatedAt() {
        return mPlaceCreatedAt;
    }
    public void sePlaceCreatedAt(Date mPlaceCreatedAt) {
        this.mPlaceCreatedAt = mPlaceCreatedAt;
    }

    public Date getPlaceModifiedAt() {
        return mPlaceModifiedAt;
    }
    public void setPlaceModifiedAt(Date mPlaceModifiedAt) {
        this.mPlaceModifiedAt = mPlaceModifiedAt;
    }

    public Date getPlaceDate() {
        return mPlaceDate;
    }
    public void setPlaceDate(Date mPlaceDate) {
        this.mPlaceDate = mPlaceDate;
    }
}
