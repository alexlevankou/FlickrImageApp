package by.alexlevankou.flickrimageapp.model;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("id")
    public String mId;

    @SerializedName("secret")
    public String mSecret;

    @SerializedName("title")
    public String mTitle;

    @SerializedName("server")
    public String mServer;

    @SerializedName("farm")
    public int mFarm;

    public String toString() {
        return "Photo{" +
                "mId=" + mId +
                ", mSecret='" + mSecret + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mServer=" + mServer +
                '}';
    }
}
