package ss.com.Kotlin_MVP_Rx.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ss.com.Kotlin_MVP_Rx.R
import ss.com.Kotlin_MVP_Rx.component.imageloading.ImageLoadingService
import ss.com.Kotlin_MVP_Rx.main.data.NewsViewModel

/**
 *@author S.Shahini
 *@since 10/3/17
 */
class NewsAdapter constructor(val newses: List<NewsViewModel>, val onNewsClick: NewsViewHolder.OnNewsClick, val imageLoadingService: ImageLoadingService) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_news, parent, false),
                onNewsClick,
                imageLoadingService)
    }

    override fun getItemCount(): Int {
        return newses.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder?, position: Int) {
        holder?.bindNews(newses[position])
    }

    class NewsViewHolder(itemView: View?, val onNewsClick: OnNewsClick, val imageLoadingService: ImageLoadingService) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView? = itemView?.findViewById(R.id.tv_news_title)
        val authorTextView: TextView? = itemView?.findViewById(R.id.tv_news_author)
        val dateTextView: TextView? = itemView?.findViewById(R.id.tv_news_date)
        val descriptionTextView: TextView? = itemView?.findViewById(R.id.tv_news_description)
        val newsImageVIew: ImageView? = itemView?.findViewById(R.id.iv_news_newsImage)
        fun bindNews(news: NewsViewModel) {

            titleTextView?.text = news.title
            descriptionTextView?.text = news.description
            dateTextView?.text = news.date
            authorTextView?.text = news.author
            imageLoadingService.loadImage(news.imageUrl, newsImageVIew)
            itemView.setOnClickListener {
                onNewsClick.onNewsClick(news)
            }
        }

        interface OnNewsClick {
            fun onNewsClick(news: NewsViewModel)
        }
    }
}