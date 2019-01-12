package by.alexlevankou.flickrimageapp.network;

import java.util.List;

import by.alexlevankou.flickrimageapp.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceholderService {
    @GET("/posts")
    Call<List<Post>> getPosts();
}
