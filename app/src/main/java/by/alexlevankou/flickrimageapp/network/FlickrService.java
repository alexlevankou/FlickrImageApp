package by.alexlevankou.flickrimageapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import by.alexlevankou.flickrimageapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class FlickrService {

    private static FlickrService mInstance;
    private static final String API_URL = BuildConfig.FLICKR_API;
    private static final String API_KEY = BuildConfig.FLICKR_KEY;
    private Retrofit mRetrofit;

    private FlickrService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new ApiKeyInterceptor(API_KEY));

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                //.client(client.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
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
