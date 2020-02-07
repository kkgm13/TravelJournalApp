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
import java.util.List;

@Entity(tableName = "holiday_places")
public class HolidayPlace implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mHolidayPlaceID;

    @Embedded
    public Holiday holiday;

    @Relation(parentColumn = "pID", entityColumn = "associatedHolidayID")
    public List<VisitedPlace> placesVisited;

}
