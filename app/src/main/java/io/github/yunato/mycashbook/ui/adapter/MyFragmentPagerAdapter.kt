package io.github.yunato.mycashbook.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.yunato.mycashbook.ui.fragment.InputRecordFragment
import io.github.yunato.mycashbook.ui.fragment.ListBaseFragment

class MyFragmentPagerAdapter(fm: FragmentManager, val money: Long) : FragmentPagerAdapter(fm) {

    val tabTitles: Array<CharSequence> = arrayOf("入力", "記録")

    override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> InputRecordFragment.newInstance()
            1 -> ListBaseFragment.newInstance(money)
            else -> null
        }
    }

    override fun getCount(): Int = tabTitles.size
}
