package by.alexlevankou.flickrimageapp.model;

import com.google.gson.annotations.SerializedName;

public class FlickrResult {

    @SerializedName("photos")
    public FlickrPhotos mPhotosInfo;

    @SerializedName("stat")
    public String mStatus;

    @Override
    public String toString() {
        return "GetRecentPhotosResponse{" +
                "mPhotosInfo=" + mPhotosInfo +
                ", mStatus='" + mStatus + '\'' +
                '}';
    }
}
