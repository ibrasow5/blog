package com.example.blog

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date

class ArticleDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        val titleTextView: TextView = findViewById(R.id.articleTitleTextView)
        val contentTextView: TextView = findViewById(R.id.articleContentTextView)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")

        titleTextView.text = title
        contentTextView.text = content

        val returnButton: ImageButton = findViewById(R.id.returnButton)
        returnButton.setOnClickListener {
            finish()

        }
    }
}
