package com.example.blog

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter(private val articleList: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.titleTextView.text = article.title

        val maxLength = 100 // Nombre maximal de caractères pour l'aperçu
        val contentPreview = if (article.content.length > maxLength) {
            article.content.substring(0, maxLength) + "..." // Ajoute des points de suspension si le contenu dépasse la longueur maximale
        } else {
            article.content
        }
        holder.contentTextView.text = contentPreview

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ArticleDetailsActivity::class.java)
            intent.putExtra("title", article.title)
            intent.putExtra("content", article.content)
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return articleList.size
    }
}
