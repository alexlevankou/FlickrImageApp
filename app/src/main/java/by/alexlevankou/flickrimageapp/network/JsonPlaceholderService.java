package by.alexlevankou.flickrimageapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import by.alexlevankou.flickrimageapp.BuildConfig;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class JsonPlaceholderService {

    private static JsonPlaceholderService mInstance;
    private static final String API_URL = BuildConfig.JSONPLACEHOLDER_API;
    private Retrofit mRetrofit;

    private JsonPlaceholderService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder().setLenient().create();

        mRetrofit = new Retrofit.Builder().baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static JsonPlaceholderService getInstance() {
        if (mInstance == null) {
            mInstance = new JsonPlaceholderService();
        }
        return mInstance;
    }

    public JsonPlaceholderApi getJsonPlaceholderApi() {
        return mRetrofit.create(JsonPlaceholderApi.class);
    }
}