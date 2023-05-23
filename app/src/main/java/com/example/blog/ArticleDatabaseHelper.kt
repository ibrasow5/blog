package com.example.blog


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ArticleDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_ARTICLES (" +
                "$COLUMN_TITLE TEXT," +
                "$COLUMN_CONTENT TEXT)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_ARTICLES"
        db.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertArticle(article: Article): Long {
        val values = ContentValues()
        values.put(COLUMN_TITLE, article.title)
        values.put(COLUMN_CONTENT, article.content)
        return writableDatabase.insert(TABLE_ARTICLES, null, values)
    }

    fun deleteArticle(title: String): Boolean {
        val db = this.writableDatabase
        val whereClause = "$COLUMN_TITLE = ?"
        val whereArgs = arrayOf(title)
        val deletedRows = db.delete(TABLE_ARTICLES, whereClause, whereArgs)
        db.close()

        return deletedRows > 0
    }


    @SuppressLint("Range")
    fun getAllArticles(): List<Article> {
        val articles = mutableListOf<Article>()
        val query = "SELECT * FROM $TABLE_ARTICLES"
        val cursor = readableDatabase.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT))
            val article = Article(title, content)
            articles.add(article)
        }
        cursor.close()
        return articles
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ArticleDatabase"
        private const val TABLE_ARTICLES = "articles"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
    }
}

