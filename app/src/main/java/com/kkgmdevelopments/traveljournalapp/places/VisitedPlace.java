package com.kkgmdevelopments.traveljournalapp.places;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

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
    @ColumnInfo(name = "associatedHolidayID")
    public long associatedHolidayID;
    @NonNull
    @ColumnInfo(name = "placeName")
    private String mPlaceName;
//    private Date mPlaceDate;
//    private String mPlaceLocation;
    @ColumnInfo(name = "placeNotes")
    private String mPlaceNotes;
//    private final int mPlacePhotos;
//    https://medium.com/androiddevelopers/database-relations-with-room-544ab95e4542

    @Ignore
    public VisitedPlace(){

    }


    // Constructor
    public VisitedPlace(String mPlaceName){
        this.mPlaceName = mPlaceName;
//        this.mStartDate = mStartDate;
//        this.mEndDate = mEndDate;
//        this.mPlaceNotes = mPlaceNotes;
    }

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

    public String getPlaceNotes() {
        return mPlaceNotes;
    }

    public void setPlaceNotes(String mPlaceNotes) {
        this.mPlaceNotes = mPlaceNotes;
    }
}
