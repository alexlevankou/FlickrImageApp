package by.alexlevankou.flickrimageapp.network;

import android.support.annotation.NonNull;

import by.alexlevankou.flickrimageapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiFactory {

    private static OkHttpClient sClient;
    private static volatile PlaceholderService sPlaceholderService;

    private ApiFactory() {
    }

    @NonNull
    public static PlaceholderService getPlaceholderService() {
        PlaceholderService service = sPlaceholderService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sPlaceholderService;
                if (service == null) {
                    service = sPlaceholderService = buildRetrofit().create(PlaceholderService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_JSONPLACEHOLDER)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
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
                .addInterceptor(new ApiKeyInterceptor())
                .build();
    }
}
