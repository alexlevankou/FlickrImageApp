package by.alexlevankou.flickrimageapp.model;

import com.google.gson.annotations.SerializedName;

public class FlickrResult {

    @SerializedName("photos")
    private FlickrPhotos flickrPhotos;

    @SerializedName("stat")
    private String status;

    public FlickrPhotos getFlickrPhotos() {
        return flickrPhotos;
    }

    public void setFlickrPhotos(FlickrPhotos flickrPhotos) {
        this.flickrPhotos = flickrPhotos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FlickrResult{" +
                "flickrPhotos=" + flickrPhotos +
                ", status='" + status + '\'' +
                '}';
    }
}
