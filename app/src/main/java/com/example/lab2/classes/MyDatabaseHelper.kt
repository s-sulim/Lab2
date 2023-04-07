package com.example.lab2.classes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "AnimeDatabase.db"
        private const val TABLE_NAME = "AnimesTable"

        private const val COLUMN_NAME = "name"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_DETAILED_DESCRIPTION = "detailed_description"
        private const val COLUMN_POSTER_URL = "poster_url"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COLUMN_NAME TEXT, $COLUMN_DESCRIPTION TEXT, $COLUMN_DETAILED_DESCRIPTION TEXT, $COLUMN_POSTER_URL TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }

    fun addAnime(anime:Anime): Long {
        val values = ContentValues().apply {
            put(COLUMN_NAME, anime.name)
            put(COLUMN_DESCRIPTION, anime.description)
            put(COLUMN_DETAILED_DESCRIPTION, anime.detailedDescription)
            put(COLUMN_POSTER_URL, anime.posterUrl)
        }

        val db = writableDatabase
        val newRowId = db.insert(TABLE_NAME, null, values)
        db.close()
        return newRowId
    }

    fun getAllAnimes(): MutableList<Anime> {
        val animes = mutableListOf<Anime>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_DETAILED_DESCRIPTION, COLUMN_POSTER_URL), null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
                val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val detailedDescription = getString(getColumnIndexOrThrow(COLUMN_DETAILED_DESCRIPTION))
                val posterUrl = getString(getColumnIndexOrThrow(COLUMN_POSTER_URL))
                animes.add(Anime(name, description, detailedDescription, posterUrl))
            }
        }
        cursor.close()
        db.close()
        return animes
    }
}