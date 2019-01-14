package by.alexlevankou.flickrimageapp.presenter;

import java.util.List;

import by.alexlevankou.flickrimageapp.model.FlickrPost;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class BaseContract {

    public interface View {
    }

    public interface Presenter {
        //void onActivityCreated();
        void onDestroy();
    }

    public interface Model {
        void addPost(FlickrPost flickrPost);
        void updatePosts();
        Flowable<FlickrPost> getPost(int postId);
        Flowable<List<FlickrPost>> getAllPosts();
        Observable<FlickrPost> requestData();
    }
}
