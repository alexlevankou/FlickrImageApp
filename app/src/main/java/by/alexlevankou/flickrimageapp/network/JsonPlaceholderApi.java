package by.alexlevankou.flickrimageapp.network;

import java.util.List;

import by.alexlevankou.flickrimageapp.model.Post;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {
    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("/posts")
    Observable<List<Post>> getPostsObservable();
}
