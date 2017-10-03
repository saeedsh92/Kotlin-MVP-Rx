package ss.com.Kotlin_MVP_Rx.component.httpclient.okhttp;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import ss.com.Kotlin_MVP_Rx.BuildConfig;
import ss.com.Kotlin_MVP_Rx.component.httpclient.HttpClient;
import ss.com.Kotlin_MVP_Rx.component.httpclient.HttpRequest;

/**
 * @author S.Shahini
 * @since 8/16/17
 */

public class OkHttp3HttpClient implements HttpClient {
    protected static OkHttpClient okHttpClient;

    public OkHttp3HttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        okHttpClient = new okhttp3.OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Override
    public <T> HttpRequest makeRequest(String endPointUrl, Class<T> responseType) {
        return this.makeRequest(HttpRequest.Method.GET, endPointUrl, null, null,responseType);
    }

    @Override
    public <T> HttpRequest makeRequest(HttpRequest.Method method, String endPointUrl, Class<T> responseType) {
        return this.makeRequest(method, endPointUrl, null, null,responseType);
    }

    @Override
    public <T> HttpRequest makeRequest(HttpRequest.Method method, String endPointUrl, HashMap<String, String> queryStrings, Class<T> responseType) {
        return this.makeRequest(method, endPointUrl, queryStrings, null,responseType);
    }

    @Override
    public <T> HttpRequest makeRequest(HttpRequest.Method method, HashMap<String, String> body, String endPointUrl, Class<T> responseType) {
        return this.makeRequest(method, endPointUrl, null, body,responseType);
    }

    @Override
    public <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String endPointUrl, HashMap<String, String> queryStrings, HashMap<String, String> requestBody, Class<T> responseType) {
        return new OkHttpRequest<>(method,getBaseUrl()+endPointUrl,queryStrings,requestBody,responseType);
    }

    @Override
    public String getBaseUrl() {
        return BuildConfig.API_BASE_URL;
    }
}
