package com.kkgmdevelopments.traveljournalapp.images;

import android.os.Parcel;
import android.os.Parcelable;

public class SpacePhoto implements Parcelable {

    private String mURL;
    private String mTitle;

    /**
     * Public Constructor
     * @param mURL
     * @param mTitle
     */
    public SpacePhoto(String mURL, String mTitle) {
        this.mTitle = mTitle;
        this.mURL = mURL;
    }

    /**
     * Protected Constructor
     * @param in
     */
    protected SpacePhoto(Parcel in) {
        mURL = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<SpacePhoto> CREATOR = new Creator<SpacePhoto>() {
        @Override
        public SpacePhoto createFromParcel(Parcel in) {
            return new SpacePhoto(in);
        }

        @Override
        public SpacePhoto[] newArray(int size) {
            return new SpacePhoto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mURL);
        parcel.writeString(mTitle);
    }

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

    public static SpacePhoto[] getSpacePhotos() {
        return new SpacePhoto[]{
                new SpacePhoto("http://i.imgur.com/zuG2bGQ.jpg", "Galaxy"),
                new SpacePhoto("http://i.imgur.com/ovr0NAF.jpg", "Space Shuttle"),
                new SpacePhoto("http://i.imgur.com/n6RfJX2.jpg", "Galaxy Orion"),
                new SpacePhoto("http://i.imgur.com/qpr5LR2.jpg", "Earth"),
                new SpacePhoto("http://i.imgur.com/pSHXfu5.jpg", "Astronaut"),
                new SpacePhoto("http://i.imgur.com/3wQcZeY.jpg", "Satellite"),
        };
    }
}
