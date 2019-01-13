package by.alexlevankou.flickrimageapp.network;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class JsonPlaceholderService {

    private static OkHttpClient sClient;
    private static volatile JsonPlaceholderApi sPlaceholderApi;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private JsonPlaceholderService() {
    }

    @NonNull
    public static JsonPlaceholderApi getPlaceholderService() {
        JsonPlaceholderApi api = sPlaceholderApi;
        if (api == null) {
            synchronized (JsonPlaceholderService.class) {
                api = sPlaceholderApi;
                if (api == null) {
                    api = sPlaceholderApi = buildRetrofit().create(JsonPlaceholderApi.class);
                }
            }
        }
        return api;
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (JsonPlaceholderService.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                //.addInterceptor(new ApiKeyInterceptor())
                .build();
    }
}