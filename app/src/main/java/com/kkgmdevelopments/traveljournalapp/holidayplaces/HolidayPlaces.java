package com.kkgmdevelopments.traveljournalapp.holidayplaces;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlace;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

//@Entity(tableName = "holiday_places")
public class HolidayPlaces implements Serializable {
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    private int mHolPlacesID;

    @Embedded
    public Holiday holiday; // Call Listed Holiday

    // Relationship
    @Relation(parentColumn = "hID", entity = VisitedPlace.class, entityColumn = "holidayID")
    public List<VisitedPlace> placesVisited = Collections.emptyList();

}
