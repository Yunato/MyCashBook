package io.github.yunato.mycashbook.ui.activity

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recordDB = RecordDBAdapter(this)
        val records = recordDB.getRecords()
        for (record in records) {
            RecordContent.ITEMs.add(record)
        }

        val adapter: MyFragmentPagerAdapter = MyFragmentPagerAdapter(supportFragmentManager)
        val viewPager = findViewById(R.id.view_pager) as ViewPager
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = adapter

        val tabLayout = findViewById(R.id.tab_layout) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onSave(date: Long, money: Long, content: String, fluctuation: String) {
        recordDB.addRecord(date, money, content, fluctuation)
    }

    override fun onSelect(item: Record) {
    }
}
