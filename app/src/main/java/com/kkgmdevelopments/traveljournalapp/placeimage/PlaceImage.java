package com.kkgmdevelopments.traveljournalapp.placeimage;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.kkgmdevelopments.traveljournalapp.images.Photo;

@Entity(tableName = "places_images_table")
public class PlaceImage {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "placeID")
    private int placeID;
    @Embedded
    private Photo image;

    @Ignore
    public PlaceImage(int id, int placeID, Photo image){
        this.id = id;
        this.placeID = placeID;
        this.image = image;
    }

    public PlaceImage(int placeID, Photo image){
        this.placeID = placeID;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public Photo getImage() {
        return image;
    }

    public void setImage(Photo image) {
        this.image = image;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int flags) {
//        parcel.writeInt(placeID);
////        parcel.writeP
//    }
}
