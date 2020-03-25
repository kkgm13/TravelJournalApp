package com.kkgmdevelopments.traveljournalapp.holidayplaces;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlace;
import java.io.Serializable;
import java.util.List;

/**
 * Room Relationship Entity Model (NOT IN USE)
 *  This is the that provides connection between Holiday Model & VisitedPlace Model
 *  @TODO: Initialise Google's Method for Room Relationships
 */
public class HolidayPlaces implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mHolPlacesID;

    @Embedded
    public Holiday holiday; // Call Listed Holiday

    // Relationship
    @Relation(parentColumn = "hID",
            entityColumn = "holidayID")
    public List<VisitedPlace> placesVisited;

    // Encapsulation Methods //
    public int getMHolPlacesID() {
        return mHolPlacesID;
    }
    public void setMHolPlacesID(int mHolPlacesID) {
        this.mHolPlacesID = mHolPlacesID;
    }
}
