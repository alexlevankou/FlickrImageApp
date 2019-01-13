package by.alexlevankou.flickrimageapp.presenter;

import android.arch.lifecycle.LiveData;

import java.util.List;

import by.alexlevankou.flickrimageapp.model.PostAndPhoto;

public class MainContract {

    public interface View {
    }

    public interface Presenter {
        void onActivityCreated();
        void onDestroy();
    }

    public interface Repository {
        LiveData<PostAndPhoto> getPost(int postId);
        LiveData<List<PostAndPhoto>> getAllPosts();
    }
}
