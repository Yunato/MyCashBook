package io.github.yunato.mycashbook.model.dto

import android.os.Parcel
import android.os.Parcelable

data class Record(val date: Long,
                  val money: Long,
                  val content: String,
                  val fluctuation: String) : Parcelable {

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Record> = object : Parcelable.Creator<Record> {
            override fun createFromParcel(source: Parcel): Record = source.run {
                Record(readLong(), readLong(), readString(), readString())
            }

            override fun newArray(size: Int): Array<Record?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeLong(date)
            writeLong(money)
            writeString(content)
            writeString(fluctuation)
        }
    }
}
