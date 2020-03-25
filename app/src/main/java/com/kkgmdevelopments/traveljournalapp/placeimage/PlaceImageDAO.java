package com.kkgmdevelopments.traveljournalapp.placeimage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * PlaceImage Data Access Object
 *  This is the Data Access Object between the repository and the Database
 */
@Dao
public interface PlaceImageDAO {
    @Insert
    void insertImage(PlaceImage image);

    @Delete
    void deleteImage(PlaceImage image);

    @Query("DELETE FROM places_images_table")
    void deleteAllImages();

    @Query("SELECT * FROM places_images_table WHERE placeID = :id")
    List<PlaceImage> getAllPlaceImages(int id);

    @Query("SELECT * FROM places_images_table")
    List<PlaceImage> getAllImages();
}
