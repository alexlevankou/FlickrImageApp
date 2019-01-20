package by.alexlevankou.flickrimageapp.repository;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import by.alexlevankou.flickrimageapp.BuildConfig;
import by.alexlevankou.flickrimageapp.model.FlickrPost;
import by.alexlevankou.flickrimageapp.model.Photo;
import by.alexlevankou.flickrimageapp.model.Post;
import by.alexlevankou.flickrimageapp.network.FlickrService;
import by.alexlevankou.flickrimageapp.network.JsonPlaceholderService;
import by.alexlevankou.flickrimageapp.presenter.BaseContract;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class Repository implements BaseContract.Model {

    private PostAndPhotoDao mPostDao;
    private List<FlickrPost> postList = new ArrayList<>();

    public Repository(PostAndPhotoDao postDao) {
        this.mPostDao = postDao;
    }

    @Override
    public Flowable<FlickrPost> getPost(int postId) {
        return mPostDao.getPostById(postId);
    }

    @Override
    @Nullable
    public Flowable<List<FlickrPost>> getAllPosts() {
        return mPostDao.getAllPosts();
    }

    @Override
    public void addPost(FlickrPost flickrPost) {
        flickrPost.setId(flickrPost.getPost().getId());
        postList.add(flickrPost);
    }

    @Override
    public void updatePosts() {
        new Thread(() -> mPostDao.insertPosts(postList)).start();
    }

    @Override
    public Observable<FlickrPost> requestData() {
        postList.clear();

        Observable<Post> postObservable = JsonPlaceholderService
                .getInstance()
                .getJsonPlaceholderApi()
                .getPostsObservable()
                .subscribeOn(Schedulers.newThread())
                .flatMapIterable(item->item);

        Observable<Photo> photoObservable = FlickrService
                .getInstance()
                .getFlickrApi()
                .getRecentPhotosObservable(BuildConfig.FLICKR_KEY ,100, 1, "json",1)
                .subscribeOn(Schedulers.newThread())
                .map(result -> result.getFlickrPhotos().getPhotoList())
                .flatMapIterable(item->item);

        return Observable.zip(
                postObservable,
                photoObservable,
                FlickrPost::new)
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread());
    }
}
