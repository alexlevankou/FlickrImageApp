package by.alexlevankou.flickrimageapp.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

    private String apiKey;

    ApiKeyInterceptor(String apiKey){
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("appid", apiKey)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
