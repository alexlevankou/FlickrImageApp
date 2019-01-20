package by.alexlevankou.flickrimageapp.network;

import by.alexlevankou.flickrimageapp.BuildConfig;


public final class JsonPlaceholderService extends BaseService {

    private static JsonPlaceholderService mInstance;

    private JsonPlaceholderService() {
        apiUrl = BuildConfig.JSONPLACEHOLDER_API;
        buildRetrofit();
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