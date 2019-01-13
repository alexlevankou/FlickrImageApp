package by.alexlevankou.flickrimageapp.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import java.util.List;

import by.alexlevankou.flickrimageapp.BuildConfig;
import by.alexlevankou.flickrimageapp.model.FlickrResult;
import by.alexlevankou.flickrimageapp.model.Post;
import by.alexlevankou.flickrimageapp.network.FlickrService;
import by.alexlevankou.flickrimageapp.network.JsonPlaceholderService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private PostDao mPostDao;

    public Repository(PostDao postDao) {
        this.mPostDao = postDao;
    }

    public void addPost(Post post) {
        mPostDao.insert(post);
    }

    public LiveData<Post> getPost(int postId) {
        return mPostDao.getPostById(postId);
    }

    @Nullable
    public LiveData<List<Post>> getAllPosts() {
        LiveData<List<Post>> mPostsData = mPostDao.getAllPosts();
        refreshPosts();
        return mPostsData;
    }

    private void refreshPosts() {
        getPhotos();
        JsonPlaceholderService.getPlaceholderService().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Runnable r = new ApiRequestRunnable(response);
                new Thread(r).start();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                boolean d = false;
            }
        });
    }

    public class ApiRequestRunnable implements Runnable {

        private Response<List<Post>> response;

        ApiRequestRunnable(Response<List<Post>>  response) {
            this.response = response;
        }

        public void run() {
            mPostDao.insertPosts(response.body());
        }
    }

    public void getPhotos() {
        FlickrService.getInstance().getFlickrApi().getRecentPhotos(BuildConfig.FLICKR_KEY ,100, 1, "json",1).enqueue(new Callback<FlickrResult>() {
            @Override
            public void onResponse(Call<FlickrResult> call, Response<FlickrResult> response) {
                boolean a = true;
            }

            @Override
            public void onFailure(Call<FlickrResult> call, Throwable t) {
                boolean d = false;
            }
        });

    }

}
