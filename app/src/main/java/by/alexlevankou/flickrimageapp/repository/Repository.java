package by.alexlevankou.flickrimageapp.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import java.util.List;

import by.alexlevankou.flickrimageapp.model.Post;

public class Repository {

    private PostDao mPostDao;
    private LiveData<List<Post>> mPostsData;

    public Repository(PostDao postDao) {
        this.mPostDao = postDao;
    }

    public LiveData<Post> getPost(int postId) {
        return mPostDao.getPostById(postId);
    }

    @Nullable
    public LiveData<List<Post>> getAllPosts() {
        mPostsData = mPostDao.getAllPosts();
        return mPostsData;
    }

    public void addPost(Post post) {
        mPostDao.insert(post);
    }
}
