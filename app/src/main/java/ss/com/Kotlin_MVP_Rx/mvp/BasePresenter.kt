package ss.com.Kotlin_MVP_Rx.mvp

/**
 *@author S.Shahini
 *@since 9/11/17
 */
interface BasePresenter<A> {
    fun attachView(view: A)
    fun detachView()
}