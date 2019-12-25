package com.kkgmdevelopments.traveljournalapp.places;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Visited Place Model
 *
 * All associated with Places visited in the Holiday
 * TODO: Create relationship with Holiday and VisitedPlace
 */
@Entity(tableName = "places_table")
public class VisitedPlace implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int mPlaceID;
    @NonNull
    @ColumnInfo(name = "placeName")
    private String mPlaceName;
//    private Date mPlaceDate;
//    private String mPlaceLocation;
    @ColumnInfo(name = "placeNotes")
    private String mPlaceNotes;
//    private final int mPlacePhotos;



    public int getmPlaceID() {
        return mPlaceID;
    }

    public void setmPlaceID(int mPlaceID) {
        this.mPlaceID = mPlaceID;
    }

    public String getmPlaceName() {
        return mPlaceName;
    }

    public void setmPlaceName(String mPlaceName) {
        this.mPlaceName = mPlaceName;
    }

    public String getmPlaceNotes() {
        return mPlaceNotes;
    }

    public void setmPlaceNotes(String mPlaceNotes) {
        this.mPlaceNotes = mPlaceNotes;
    }
}
