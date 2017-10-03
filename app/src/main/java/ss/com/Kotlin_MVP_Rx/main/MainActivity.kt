package ss.com.Kotlin_MVP_Rx.main

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ss.com.Kotlin_MVP_Rx.R
import ss.com.Kotlin_MVP_Rx.component.httpclient.HttpClientInjector
import ss.com.Kotlin_MVP_Rx.component.imageloading.ImageLoadingServiceInjector
import ss.com.Kotlin_MVP_Rx.detail.NewsDetail
import ss.com.Kotlin_MVP_Rx.main.data.NewsViewModel

class MainActivity : AppCompatActivity(), MainContract.View, SwipeRefreshLayout.OnRefreshListener, NewsAdapter.NewsViewHolder.OnNewsClick {

    lateinit var presenter: MainContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(HttpClientInjector.inject())
        setupViews()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    private fun setupViews() {
        rv_home_news.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        refreshLayout_home.setOnRefreshListener(this)
    }

    override fun getViewContext(): Context {
        return this
    }

    override fun setProgressIndicator(mustShow: Boolean) {
        if (mustShow)
            progressBar_home.visibility = View.VISIBLE
        else {
            progressBar_home.visibility = View.GONE
            refreshLayout_home.isRefreshing = false
        }
    }

    override fun showNewses(newses: List<NewsViewModel>) {
        rv_home_news.adapter = NewsAdapter(newses, this, ImageLoadingServiceInjector.getImageLoadingService(this))
    }

    override fun onRefresh() {
        presenter.onRefresh()
    }

    override fun showError(message: String) {
        Snackbar.make(coordinatorLayout_main, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showNewsDetail(news: NewsViewModel) {
        NewsDetail.start(this, news)
    }

    override fun onNewsClick(news: NewsViewModel) {
        presenter.onNewsClick(news)
    }
}
