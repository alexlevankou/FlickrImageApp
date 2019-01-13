package by.alexlevankou.flickrimageapp.network;



import by.alexlevankou.flickrimageapp.model.FlickrResult;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrApi {

    @GET("/services/rest/?method=flickr.photos.getRecent")
    public Call<FlickrResult> getRecentPhotos(
            @Query("api_key") String apiKey,
            @Query("per_page") int perPage,
            @Query("page") int page,
            @Query("format") String format,
            @Query("nojsoncallback") int nojsoncallback
    );

    @GET("/services/rest/?method=flickr.photos.getRecent")
    public Observable<FlickrResult> getRecentPhotosObservable(
            @Query("api_key") String apiKey,
            @Query("per_page") int perPage,
            @Query("page") int page,
            @Query("format") String format,
            @Query("nojsoncallback") int nojsoncallback
    );
}
