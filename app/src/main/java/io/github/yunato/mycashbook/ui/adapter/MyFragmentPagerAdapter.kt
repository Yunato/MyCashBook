package io.github.yunato.mycashbook.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import io.github.yunato.mycashbook.ui.fragment.InputRecordFragment
import io.github.yunato.mycashbook.ui.fragment.ListBaseFragment
import io.github.yunato.mycashbook.ui.fragment.RecordListFragment

class MyFragmentPagerAdapter(fm: FragmentManager, val money: Long) : FragmentPagerAdapter(fm) {

    val tabTitles: Array<CharSequence> = arrayOf("入力", "記録")
    var position: Int = 0
    var baseFragment: ListBaseFragment? = null
    var listFragment: RecordListFragment? = null

    override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> InputRecordFragment.newInstance()
            1 -> {
                baseFragment = ListBaseFragment.newInstance(money)
                baseFragment
            }
            else -> null
        }
    }

    override fun getCount(): Int = tabTitles.size

    override fun setPrimaryItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.setPrimaryItem(container, position, `object`)

        listFragment = baseFragment?.listFragment
    }
}
