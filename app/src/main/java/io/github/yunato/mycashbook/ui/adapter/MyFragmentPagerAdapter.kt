package io.github.yunato.mycashbook.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.yunato.mycashbook.ui.fragment.InputRecordFragment
import io.github.yunato.mycashbook.ui.fragment.RecordListFragment

class MyFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val tabTitles: Array<CharSequence> = arrayOf("タブ1", "タブ2")

    override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> InputRecordFragment.newInstance()
            1 -> RecordListFragment.newInstance()
            else -> null
        }
    }

    override fun getCount(): Int = tabTitles.size
}
