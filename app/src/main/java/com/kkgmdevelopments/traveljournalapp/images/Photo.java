package com.kkgmdevelopments.traveljournalapp.images;

import java.io.Serializable;

/**
 * Photo Class
 *  This represents the Photo/Image used for the Gallery.
 */
public class Photo implements Serializable {

    private String mURL;        // Path of the Image
    private String mTitle;      // Title of the Image

    /**
     * Public Constructor
     * @param mURL
     * @param mTitle
     */
    public Photo(String mURL, String mTitle) {
        this.mTitle = mTitle;
        this.mURL = mURL;
    }

    // Encapsulation Methods //
    public String getURL() {
        return mURL;
    }
    public void setURL(String mURL) {
        this.mURL = mURL;
    }

    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Get all Sample Images to pass for the Photo Gallery
     * @return Array of Sample Photos
     */
    public static Photo[] getSpacePhotos() {
        return new Photo[]{
                new Photo("https://i.imgur.com/zuG2bGQ.jpg", "Galaxy"),
                new Photo("https://i.imgur.com/ovr0NAF.jpg", "Space Shuttle"),
                new Photo("https://i.imgur.com/n6RfJX2.jpg", "Galaxy Orion"),
                new Photo("https://i.imgur.com/qpr5LR2.jpg", "Earth"),
                new Photo("https://i.imgur.com/pSHXfu5.jpg", "Astronaut"),
                new Photo("https://i.imgur.com/3wQcZeY.jpg", "Satellite"),
        };
    }

    //// If implementing Parcelable
//    /**
//     * Protected Constructor
//     * @param in
//     */
//    protected Photo(Parcel in) {
//        in.readInt();
//        mURL = in.readString();
//        mTitle = in.readString();
//    }
//
//    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
//        @Override
//        public Photo createFromParcel(Parcel in) {
//            return new Photo(in);
//        }
//
//        @Override
//        public Photo[] newArray(int size) {
//            return new Photo[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(mURL);
//        parcel.writeString(mTitle);
//    }
}
