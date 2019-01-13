package by.alexlevankou.flickrimageapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlickrPhotos {

    @SerializedName("photo")
    private List<Photo> photoList;
    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int pages;
    @SerializedName("perpage")
    private int perPage;
    @SerializedName("total")
    private String total;

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "FlickrPhotos{" +
                "photoList=" + photoList +
                ", page=" + page +
                ", pages=" + pages +
                ", perPage=" + perPage +
                ", total=" + total +
                '}';
    }
}
