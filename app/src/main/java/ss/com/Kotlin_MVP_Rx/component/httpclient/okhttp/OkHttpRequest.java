package ss.com.Kotlin_MVP_Rx.component.httpclient.okhttp;

import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ss.com.Kotlin_MVP_Rx.component.httpclient.HttpRequest;

/**
 * @author S.Shahini
 * @since 8/16/17
 */

public class OkHttpRequest<T> extends HttpRequest<T> {
    private static final String TAG = "OkHttpRequest";
    private Request request;
    private Call call;

    public OkHttpRequest(HttpRequest.Method method, String endPointUrl, HashMap<String, String> queryStrings, HashMap<String, String> body, Class<T> responseType) {
        super(method, endPointUrl, queryStrings, body, responseType);
        state = State.PENDING;
    }

    @Override
    public Observable<T> send() {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception {
                OkHttpClient okHttpClient = OkHttp3HttpClient.okHttpClient;

                request = new Request.Builder().url(endPointUrl + convertUrlQueryParamsToString(urlParams)).build();
                call = okHttpClient.newCall(request);
                state = State.SENT;
                Log.i(TAG, "Request is sending to: " + request.url());
                Response response = call.execute();
                state = State.SUCCESS;
                try {
                    Gson gson = new Gson();
                    T res = (T) gson.fromJson(response.body().string(), responseType);
                    emitter.onNext(res);
                    emitter.onComplete();

                } catch (Exception e) {
                    state = State.ERROR;
                    Log.e(TAG, "onError " + e.toString());
                    emitter.onError(e);
                }
            }
        });
    }

    @Override
    public void cancel() {
        Log.i(TAG, "onCancel: The request has been canceled");
        call.cancel();
        state = State.CANCELED;
    }

    public String convertUrlQueryParamsToString(HashMap<String, String> urlParams) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        String[] keys = urlParams.keySet().toArray(new String[0]);
        String[] values = urlParams.values().toArray(new String[0]);
        for (int i = 0; i < urlParams.size(); i++) {
            stringBuilder.append(keys[i]);
            stringBuilder.append("=");
            stringBuilder.append(values[i]);
            if (i < urlParams.size() - 1) {
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }

}
