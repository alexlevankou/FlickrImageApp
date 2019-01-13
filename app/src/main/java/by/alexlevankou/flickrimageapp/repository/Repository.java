package by.alexlevankou.flickrimageapp.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import java.util.List;

import by.alexlevankou.flickrimageapp.BuildConfig;
import by.alexlevankou.flickrimageapp.model.Photo;
import by.alexlevankou.flickrimageapp.model.Post;
import by.alexlevankou.flickrimageapp.model.PostAndPhoto;
import by.alexlevankou.flickrimageapp.network.FlickrService;
import by.alexlevankou.flickrimageapp.network.JsonPlaceholderService;
import by.alexlevankou.flickrimageapp.presenter.MainContract;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class Repository implements MainContract.Repository {

    private PostAndPhotoDao mPostDao;

    public Repository(PostAndPhotoDao postDao) {
        this.mPostDao = postDao;
    }

    @Override
    public LiveData<PostAndPhoto> getPost(int postId) {
        return mPostDao.getPostById(postId);
    }

    @Override
    @Nullable
    public LiveData<List<PostAndPhoto>> getAllPosts() {
        LiveData<List<PostAndPhoto>> mPostsData = mPostDao.getAllPosts();
        requestData();
        return mPostsData;
    }


    private  void addPost(PostAndPhoto postAndPhoto) {
        Runnable r = new InsertRunnable(postAndPhoto);
        new Thread(r).start();
    }

    private class InsertRunnable implements Runnable {

        PostAndPhoto postAndPhoto;

        InsertRunnable(PostAndPhoto postAndPhoto) {
            postAndPhoto.setId(postAndPhoto.getPost().getId());
            this.postAndPhoto = postAndPhoto;
        }

        public void run() {
            mPostDao.insert(postAndPhoto);
        }
    }

    private void requestData() {

        Observable<Post> postObservable = JsonPlaceholderService
                .getPlaceholderService()
                .getPostsObservable()
                .flatMapIterable(item->item);

        Observable<Photo> photoObservable = FlickrService
                .getInstance()
                .getFlickrApi()
                .getRecentPhotosObservable(BuildConfig.FLICKR_KEY ,100, 1, "json",1)
                .map(result -> {return result.getFlickrPhotos().getPhotoList();})
                .flatMapIterable(item->item);

        Observable.zip(
                postObservable,
                photoObservable,
                PostAndPhoto::new)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe((postAndPhoto) -> { addPost(postAndPhoto); });
    }
}
