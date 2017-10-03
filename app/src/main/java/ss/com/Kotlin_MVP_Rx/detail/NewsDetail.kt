package ss.com.Kotlin_MVP_Rx.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_detail.*
import ss.com.Kotlin_MVP_Rx.R
import ss.com.Kotlin_MVP_Rx.main.data.NewsViewModel

const val NEWS: String = "news"

class NewsDetail : AppCompatActivity() {
    private lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                R.layout.activity_news_detail)
        newsViewModel = intent.getParcelableExtra(NEWS)
        setupViews()
    }

    private fun setupViews() {
        tv_detail_title.text = newsViewModel.title
        tv_detail_description.text = newsViewModel.description
        tv_detail_author.text = newsViewModel.author
        tv_detail_date.text = newsViewModel.date
        Picasso.with(this).load(newsViewModel.imageUrl).into(iv_detail_newsImage)
    }

    companion object {
        fun start(context: Context, news: NewsViewModel) {
            val intent: Intent = Intent(context, NewsDetail::class.java)
            intent.putExtra(NEWS, news)
            context.startActivity(intent)
        }
    }
}
