package com.dhruvnagarajan.opennews.view.main

import android.os.Bundle
import android.view.Menu
import com.dhruvnagarajan.androidplatform.util.ext.replaceFragment
import com.dhruvnagarajan.androidplatform.view.BaseActivity
import com.dhruvnagarajan.opennews.R
import com.dhruvnagarajan.opennews.domain.entity.NewsProfile
import com.dhruvnagarajan.opennews.view.NewsFragment
import com.dhruvnagarajan.opennews.view.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Dhruvaraj Nagarajan
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav.menu
            .add(Menu.NONE, 1, Menu.NONE, "Feed")
            .icon = resources.getDrawable(R.drawable.ic_twotone_blur_on_24px)
        nav.menu
            .add(Menu.NONE, 0, Menu.NONE, "Profile")
            .icon = resources.getDrawable(R.drawable.ic_twotone_blur_on_24px)

        nav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                1 -> {
                    openFeed()
                    true
                }
                0 -> {
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
        f.profile = NewsProfile(q = "tesla")
        replaceFragment(R.id.l_container, f)
    }

    private fun openProfile() {
        val f = ProfileFragment()
        replaceFragment(R.id.l_container, f)
    }
}
