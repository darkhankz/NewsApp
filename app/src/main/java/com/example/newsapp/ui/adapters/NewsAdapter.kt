package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    private val callBack = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)

        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val article = differ.currentList[position]

        holder.itemView.apply {
            val articleImage = findViewById<ImageView>(R.id.article_image)
            val articleTitle = findViewById<TextView>(R.id.article_title)
            val articleDate = findViewById<TextView>(R.id.article_date)


            Glide.with(this).load(article.urlToImage).into(articleImage)
            articleImage.clipToOutline = true
            articleTitle.text = article.title
            articleDate.text = article.publishedAt
        }
    }

    private var onItemClickListener: ((Article)-> Unit)? = null

    fun setOnItemClickListiner(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

}