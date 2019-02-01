package io.github.yunato.mycashbook.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import io.github.yunato.mycashbook.R
import io.github.yunato.mycashbook.model.dto.Record
import io.github.yunato.mycashbook.model.dto.RecordContent
import io.github.yunato.mycashbook.ui.adapter.MyFragmentPagerAdapter
import io.github.yunato.mycashbook.ui.adapter.RecordDBAdapter
import io.github.yunato.mycashbook.ui.fragment.InputRecordFragment
import io.github.yunato.mycashbook.ui.fragment.RecordListFragment

class MainActivity : AppCompatActivity()
        , InputRecordFragment.OnSaveListener
        , RecordListFragment.OnSelectListener {

    lateinit private var recordDB: RecordDBAdapter
    lateinit var adapter: MyFragmentPagerAdapter
    val REQUEST_MONEY: Int = 1
    val PREF_MONEY: String = "money"
    var money: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefer: SharedPreferences = getSharedPreferences("money", Context.MODE_PRIVATE)
        money = prefer.getLong(PREF_MONEY, -1L)
        if (money == -1L) {
            MoneyActivity.intent(applicationContext).let { startActivityForResult(it, REQUEST_MONEY) }
        } else {
            createPage(money)
        }
    }

    fun createPage(money: Long) {
        recordDB = RecordDBAdapter(this)
        val records = recordDB.getRecords()
        for (record in records) {
            RecordContent.ITEMs.add(record)
        }

        adapter = MyFragmentPagerAdapter(supportFragmentManager, money)
        val viewPager = findViewById(R.id.view_pager) as ViewPager
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = adapter

        val tabLayout = findViewById(R.id.tab_layout) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onSave(date: Long, money: Long, content: String, fluctuation: String) {
        val id: Long = recordDB.addRecord(date, money, content, fluctuation)
        if (fluctuation.contentEquals(getString(R.string.plus_button))) {
            this.money += money
        } else if (fluctuation.contentEquals(getString(R.string.minus_button))) {
            this.money -= money
        }
        val prefer: SharedPreferences = getSharedPreferences("money", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefer.edit()
        editor.putLong(PREF_MONEY, this.money)
        editor.apply()

        RecordContent.ITEMs.add(0, RecordContent.createRecord(
                id, date, money, content, fluctuation))
        val baseFragment = adapter.baseFragment
        baseFragment?.refreshList(this.money.toString())
        val listFragment = adapter.listFragment as RecordListFragment
        listFragment.refreshList()
    }

    override fun onSelect(item: Record) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_MONEY && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                money = data.getLongExtra(MoneyActivity.EXTRA_MONEY, -1L)
                val prefer: SharedPreferences = getSharedPreferences("money", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = prefer.edit()
                editor.putLong(PREF_MONEY, money)
                editor.apply()
                createPage(money)
            }
        }
    }
}
