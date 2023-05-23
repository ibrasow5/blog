package com.example.blog

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var adapter: ArticleAdapter
    private val articleList: MutableList<Article> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisez le RecyclerView et l'adaptateur
        articlesRecyclerView = findViewById(R.id.articlesRecyclerView)
        adapter = ArticleAdapter(articleList)

        // Définissez le gestionnaire de disposition (layout manager) du RecyclerView
        val layoutManager = LinearLayoutManager(this)
        articlesRecyclerView.layoutManager = layoutManager

        // Attachez l'adaptateur au RecyclerView
        articlesRecyclerView.adapter = adapter

        val addButton: ImageButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(this, AddArticleActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_ARTICLE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_ADD_ARTICLE && resultCode == Activity.RESULT_OK) {
            val article = data?.getSerializableExtra("article") as? Article
            if (article != null) {
                articleList.add(article)
                adapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_ADD_ARTICLE = 1
    }
}