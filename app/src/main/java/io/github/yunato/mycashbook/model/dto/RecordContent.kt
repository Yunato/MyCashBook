package io.github.yunato.mycashbook.model.dto

object RecordContent {

    val ITEMs: MutableList<Record> = mutableListOf()

    val ITEM_MAP: MutableMap<String, Record> = mutableMapOf()

    init {
//        for (i in 1..COUNT) {
//            addItem(createDummyItem(i))
//        }
    }

    fun addItem(item: Record) {
        io.github.yunato.mycashbook.model.dto.RecordContent.ITEMs.add(item)
        io.github.yunato.mycashbook.model.dto.RecordContent.ITEM_MAP.put(item.id.toString(), item)
    }

    fun createRecord(id: Long,
                     date: Long,
                     money: Long,
                     content: String,
                     fluctuation: String): Record {
        return Record(id, date, money, content, fluctuation)
    }
}
