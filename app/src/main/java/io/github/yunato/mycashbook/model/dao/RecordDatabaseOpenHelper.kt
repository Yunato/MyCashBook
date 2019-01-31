package io.github.yunato.mycashbook.model.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME: String = "record.db"
val DB_VERSION: Int = 1
val DB_TABLE_NAME: String = "record"

class RecordDatabaseOpenHelper(mContext: Context) :
        SQLiteOpenHelper(mContext, DATABASE_NAME, null, DB_VERSION) {

    val FIELD_ID: String = "_id"
    val FIELD_DATE: String = "date"
    val FIELD_MONEY: String = "money"
    val FIELD_CONTENT: String = "content"
    val FIELD_FLUCTUATION: String = "fluctuation"

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
