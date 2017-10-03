package ss.com.Kotlin_MVP_Rx.component.httpclient;


import ss.com.Kotlin_MVP_Rx.component.httpclient.okhttp.OkHttp3HttpClient;

/**
 * @author S.Shahini
 * @since 8/16/17
 */

public class HttpClientInjector {
    private static HttpClient httpClient;

    public static HttpClient inject() {
        if (httpClient == null) {
            httpClient = new OkHttp3HttpClient();
        }
        return httpClient;
    }
}
