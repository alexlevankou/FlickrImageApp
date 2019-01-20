package by.alexlevankou.flickrimageapp.network;

import by.alexlevankou.flickrimageapp.BuildConfig;

public final class FlickrService extends BaseService {

    private static FlickrService mInstance;

    private FlickrService() {
        apiUrl = BuildConfig.FLICKR_API;
        buildRetrofit();
    }

    public static FlickrService getInstance() {
        if (mInstance == null) {
            mInstance = new FlickrService();
        }
        return mInstance;
    }

    public FlickrApi getFlickrApi() {
        return mRetrofit.create(FlickrApi.class);
    }
}
