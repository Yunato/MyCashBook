package io.github.yunato.mycashbook.ui.adapter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import io.github.yunato.mycashbook.model.dao.DB_TABLE_NAME
import io.github.yunato.mycashbook.model.dao.RecordDatabaseOpenHelper
import io.github.yunato.mycashbook.model.dto.Record

class RecordDBAdapter(mContext: Context) {

    private val db: SQLiteDatabase
    private val helper: RecordDatabaseOpenHelper = RecordDatabaseOpenHelper(mContext)

    init {
        db = helper.writableDatabase
    }

    fun addRecord(date: Long, money: Long, content: String, fluctuation: String) {
        val values: ContentValues = ContentValues()
        values.put(helper.FIELD_DATE, date)
        values.put(helper.FIELD_MONEY, money)
        values.put(helper.FIELD_CONTENT, content)
        values.put(helper.FIELD_FLUCTUATION, fluctuation)
        db.insertOrThrow(DB_TABLE_NAME, null, values)
    }

    fun getRecords(): MutableList<Record> {
        val sql: String = "SELECT * FROM $DB_TABLE_NAME"
        val cursor: Cursor = db.rawQuery(sql, null)
        val result: MutableList<Record> = mutableListOf()
        try {
            while (cursor.moveToNext()) {
                result.add(0, Record(
                        cursor.getLong(cursor.getColumnIndex(helper.FIELD_ID)),
                        cursor.getLong(cursor.getColumnIndex(helper.FIELD_DATE)),
                        cursor.getLong(cursor.getColumnIndex(helper.FIELD_MONEY)),
                        cursor.getString(cursor.getColumnIndex(helper.FIELD_CONTENT)),
                        cursor.getString(cursor.getColumnIndex(helper.FIELD_FLUCTUATION))))
            }
        } finally {
            cursor.close()
        }
        return result
    }

    fun updateRecord(item: Record) {
        val values: ContentValues = ContentValues()
        values.put(helper.FIELD_DATE, item.date)
        values.put(helper.FIELD_MONEY, item.money)
        values.put(helper.FIELD_CONTENT, item.content)
        values.put(helper.FIELD_FLUCTUATION, item.fluctuation)
        db.update(DB_TABLE_NAME, values, "${helper.FIELD_ID}=?", arrayOf(item.id.toString()))
    }

    fun deleteRecord(id: Long) {
        db.delete(DB_TABLE_NAME, "${helper.FIELD_ID}=?", arrayOf(id.toString()))
    }
}
