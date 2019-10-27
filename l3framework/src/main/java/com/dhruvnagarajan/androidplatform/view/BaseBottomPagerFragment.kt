package com.dhruvnagarajan.androidplatform.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhruvnagarajan.androidplatform.R
import kotlinx.android.synthetic.main.fragment_pager_bottom.view.*

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseBottomPagerFragment : BaseFragment() {

    protected lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(container?.context)
            .inflate(R.layout.fragment_pager_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(
            childFragmentManager,
            getFragments()
        )
        view.viewpager.adapter = viewPagerAdapter
        view.tablayout.setupWithViewPager(view.viewpager)
    }

    abstract fun getFragments(): List<ViewPagerAdapter.Item>
}