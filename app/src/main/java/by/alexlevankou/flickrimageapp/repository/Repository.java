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
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class Repository {

    private PostAndPhotoDao mPostDao;

    public Repository(PostAndPhotoDao postDao) {
        this.mPostDao = postDao;
    }

    public LiveData<PostAndPhoto> getPost(int postId) {
        return mPostDao.getPostById(postId);
    }

    @Nullable
    public LiveData<List<PostAndPhoto>> getAllPosts() {
        LiveData<List<PostAndPhoto>> mPostsData = mPostDao.getAllPosts();
        //refreshPosts();
        requestData();
        return mPostsData;
    }

    private  void addPost(PostAndPhoto postAndPhoto) {
        Runnable r = new InsertRunnable(postAndPhoto);
        new Thread(r).start();
    }

        public class InsertRunnable implements Runnable {

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

//    private void refreshPosts() {
//        getPhotos();
//        JsonPlaceholderService.getPlaceholderService().getPosts().enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                Runnable r = new ApiRequestRunnable(response);
//                new Thread(r).start();
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                boolean d = false;
//            }
//        });
//    }
//
//    public class ApiRequestRunnable implements Runnable {
//
//        private Response<List<Post>> response;
//
//        ApiRequestRunnable(Response<List<Post>>  response) {
//            this.response = response;
//        }
//
//        public void run() {
//            mPostDao.insertPosts(response.body());
//        }
//    }
//
//    private void getPhotos() {
//        FlickrService.getInstance().getFlickrApi().getRecentPhotos(BuildConfig.FLICKR_KEY ,100, 1, "json",1).enqueue(new Callback<FlickrResult>() {
//            @Override
//            public void onResponse(Call<FlickrResult> call, Response<FlickrResult> response) {
//                boolean a = true;
//            }
//
//            @Override
//            public void onFailure(Call<FlickrResult> call, Throwable t) {
//                boolean d = false;
//            }
//        });
//    }
}
