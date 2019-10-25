package com.dhruvnagarajan.androidcore.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author Dhruvaraj Nagarajan
 */
class ViewPagerAdapter(
    fm: FragmentManager,
    private val list: List<Item>
) : FragmentPagerAdapter(fm) {

    override fun getItem(i: Int): Fragment = list[i].fragment

    override fun getPageTitle(position: Int): CharSequence? = list[position].title

    override fun getCount(): Int = list.size

    data class Item(val fragment: Fragment, val title: String)
}
