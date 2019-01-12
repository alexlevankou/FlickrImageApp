package by.alexlevankou.flickrimageapp.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import java.util.List;

import by.alexlevankou.flickrimageapp.model.Post;
import by.alexlevankou.flickrimageapp.network.ApiFactory;
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

        ApiFactory.getPlaceholderService().getPosts().enqueue(new Callback<List<Post>>() {
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
}
