package ss.com.Kotlin_MVP_Rx.component.httpclient;

import java.util.HashMap;

/**
 * @author S.Shahini
 * @since 8/16/17
 */

public interface HttpClient {
    <T> HttpRequest makeRequest(String url, Class<T>responseType);

    <T> HttpRequest makeRequest(HttpRequest.Method method, String url, Class<T> responseType);

    <T> HttpRequest makeRequest(HttpRequest.Method method, String url, HashMap<String, String> urlParams, Class<T> responseType);

    <T> HttpRequest makeRequest(HttpRequest.Method method, HashMap<String, String> body, String url, Class<T> responseType);

    <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url, HashMap<String, String> urlParams, HashMap<String, String> requestBody, Class<T> responseType);

    String getBaseUrl();
}
