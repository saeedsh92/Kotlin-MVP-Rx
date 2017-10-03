package ss.com.Kotlin_MVP_Rx.main

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ss.com.Kotlin_MVP_Rx.BuildConfig
import ss.com.Kotlin_MVP_Rx.component.httpclient.HttpClient
import ss.com.Kotlin_MVP_Rx.component.httpclient.HttpRequest
import ss.com.Kotlin_MVP_Rx.main.data.NewsApiResponse
import ss.com.Kotlin_MVP_Rx.main.data.NewsViewModel
import java.util.*

/**
 *@author S.Shahini
 *@since 9/11/17
 */
class MainPresenter constructor(val httpClient: HttpClient) : MainContract.Presenter {

    lateinit var view: MainContract.View
    lateinit var loadNewsHttpRequest: HttpRequest<NewsApiResponse>
    override fun attachView(view: MainContract.View) {
        this.view = view
        loadNews()
    }

    override fun detachView() {
        if (loadNewsHttpRequest.isSent) {
            loadNewsHttpRequest.cancel()
        }
    }

    override fun loadNews() {
        val urlParams = HashMap<String, String>()
        urlParams.put("source", "techcrunch")
        urlParams.put("apikey", BuildConfig.API_KEY)
        loadNewsHttpRequest = httpClient.makeRequest(HttpRequest.Method.GET, "articles", urlParams, null, NewsApiResponse::class.java)
        loadNewsHttpRequest.send().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(
                object : Observer<NewsApiResponse> {
                    override fun onNext(t: NewsApiResponse) {
                        view.showNewses(t.newsViewModels)
                    }

                    override fun onComplete() {
                        view.setProgressIndicator(false)
                    }

                    override fun onError(e: Throwable) {
                        view.showError(e.toString())
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                }
        )
    }

    override fun onNewsClick(news: NewsViewModel) {
        view.showNewsDetail(news)
    }

    override fun onRefresh() {
        if (loadNewsHttpRequest.isSent) {
            loadNewsHttpRequest.cancel()
        }
        loadNews()
    }
}