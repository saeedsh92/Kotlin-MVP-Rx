package ss.com.Kotlin_MVP_Rx.main

import ss.com.Kotlin_MVP_Rx.main.data.NewsViewModel
import ss.com.Kotlin_MVP_Rx.mvp.BasePresenter
import ss.com.Kotlin_MVP_Rx.mvp.BaseView

/**
 *@author S.Shahini
 *@since 9/11/17
 */
interface MainContract {
    interface View : BaseView {
        fun setProgressIndicator(mustShow: Boolean)
        fun showNewses(newses: List<NewsViewModel>)
        fun showError(message: String)
        fun showNewsDetail(news: NewsViewModel)
    }

    interface Presenter : BasePresenter<View> {
        fun loadNews()
        fun onNewsClick(news: NewsViewModel)
        fun onRefresh()
    }

}