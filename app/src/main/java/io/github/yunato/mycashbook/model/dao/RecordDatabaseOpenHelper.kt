package io.github.yunato.mycashbook.model.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private val DATABASE_NAME: String = "record.db"
private val DB_VERSION: Int = 1
private val DB_TABLE_NAME: String = "record"


class RecordDatabaseOpenHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {

    private val FIELD_ID: String = "_id"
    private val FIELD_DATE: String = "date"
    private val FIELD_MONEY: String = "money"
    private val FIELD_CONTENT: String = "content"
    private val FIELD_FLUCTUATION: String = "fluctuation"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
                "CREATE TABLE" + DB_TABLE_NAME + " ( "
                        + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
                        + ", " + FIELD_DATE + " INTEGER"
                        + ", " + FIELD_MONEY + " INTEGER"
                        + ", " + FIELD_CONTENT + " TEXT NOT NULL"
                        + ", " + FIELD_FLUCTUATION + " TEXT NOT NULL"
                        + " );")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $DB_TABLE_NAME ;")
        onCreate(db)
    }
}
