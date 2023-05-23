package com.example.blog


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ArticleDetailsActivity : AppCompatActivity() {
    private lateinit var articleTitleTextView: TextView
    private lateinit var articleContentTextView: TextView
    private lateinit var deleteButton: Button

    private lateinit var currentArticle: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        articleTitleTextView = findViewById(R.id.articleTitleTextView)
        articleContentTextView = findViewById(R.id.articleContentTextView)
        deleteButton = findViewById(R.id.deleteButton)

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        currentArticle = Article(title.toString(), content.toString())


        articleTitleTextView.text = title
        articleContentTextView.text = content

        deleteButton.setOnClickListener {
            showDeleteConfirmationDialog()
        }

        val returnButton: ImageButton = findViewById(R.id.returnButton)
        returnButton.setOnClickListener {
            finish()
        }
    }

    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Supprimer l'article")
        builder.setMessage("Êtes-vous sûr de vouloir supprimer cet article ?")
        builder.setPositiveButton("Oui") { _, _ ->

            val db = ArticleDatabaseHelper(this)
            db.deleteArticle(currentArticle.title)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("Non", null)
        val dialog = builder.create()
        dialog.show()
    }
}
