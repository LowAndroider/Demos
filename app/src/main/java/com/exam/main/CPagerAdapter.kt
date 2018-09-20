package com.exam.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View

/**
 * @author LowAndroider
 * @date 16:13
 */

class CPagerAdapter(fm: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fm) {


    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(i: Int): Fragment {
        return fragments[i]
    }
}
