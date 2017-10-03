package ss.com.Kotlin_MVP_Rx.component.httpclient;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * @author S.Shahini
 * @since 8/16/17
 */

public abstract class HttpRequest<T> {
    protected Class<T> responseType;
    protected State state;
    protected String endPointUrl;
    protected Method method;
    protected HashMap<String, String> urlParams;
    protected HashMap<String, String> body;

    public HttpRequest(Method method, String endpointUrl, HashMap<String, String> queryStrings, HashMap<String, String> body, Class<T> responseType) {
        this.method = method;
        this.endPointUrl = endpointUrl;
        this.body = body;
        this.urlParams = queryStrings;
        this.responseType = responseType;
    }

    public abstract Observable<T> send();

    public abstract void cancel();

    protected void updateState(State state) {
        this.state = state;
    }

    public boolean isSent() {
        return state == State.SENT;
    }

    public enum Method {
        GET, POST
    }

    public enum State {
        SENT, PENDING, ERROR, SUCCESS, CANCELED
    }


}
