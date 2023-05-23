package com.example.blog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blog.Article


class AddArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article)

        val addArticleButton: Button = findViewById(R.id.addArticleButton)
        addArticleButton.setOnClickListener {
            val titleEditText: EditText = findViewById(R.id.articleTitleEditText)
            val contentEditText: EditText = findViewById(R.id.articleContentEditText)

            val title = titleEditText.text.toString().trim()
            val content = contentEditText.text.toString().trim()

            if (title.isEmpty() && content.isEmpty()) {
                Toast.makeText(this, "Veuillez saisir un titre et un contenu", Toast.LENGTH_SHORT).show()
            } else if (title.isEmpty()) {
                Toast.makeText(this, "Veuillez saisir un titre", Toast.LENGTH_SHORT).show()
            } else if (content.isEmpty()) {
                Toast.makeText(this, "Veuillez saisir le contenu", Toast.LENGTH_SHORT).show()
            } else {
                val article = Article(title, content)
                val intent = Intent()
                intent.putExtra("article", article)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        val closeButton: ImageButton = findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            finish()
        }
    }
}


private fun Intent.putExtra(s: String, article: Article) {
    TODO("Not yet implemented")
}
