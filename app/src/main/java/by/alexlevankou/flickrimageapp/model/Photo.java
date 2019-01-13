package by.alexlevankou.flickrimageapp.model;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @ColumnInfo(name = "photo_id")
    @SerializedName("id")
    private String id;

    @ColumnInfo(name = "secret")
    @SerializedName("secret")
    private String secret;

    @ColumnInfo(name = "photo_title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "server")
    @SerializedName("server")
    private String server;

    @ColumnInfo(name = "farm")
    @SerializedName("farm")
    private int farm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", secret='" + secret + '\'' +
                ", title='" + title + '\'' +
                ", server=" + server +
                ", farm=" + farm +
                '}';
    }
}
