package com.opensource.news.view.main

import android.os.Bundle
import android.view.Menu
import com.dhruvnagarajan.androidcore.view.BaseActivity
import com.dhruvnagarajan.nav.replaceFragment
import com.opensource.news.R
import com.opensource.news.view.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Dhruvaraj Nagarajan
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav.menu.add(Menu.NONE, 1, Menu.NONE, "Feed")
        nav.menu.add(Menu.NONE, 0, Menu.NONE, "Profile")

        nav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                1 -> {
                    openFeed()
                    true
                }
                2 -> {
                    openProfile()
                    true
                }
                else -> {
                    false
                }
            }
        }

        val id: Int = 1
        nav.selectedItemId = id
    }

    private fun openFeed() {
        val f = NewsFragment()
        replaceFragment(R.id.l_container, f)
    }

    private fun openProfile() {}
}
