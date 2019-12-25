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
//    https://medium.com/androiddevelopers/database-relations-with-room-544ab95e4542



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
