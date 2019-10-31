package com.dhruvnagarajan.opennews.view.main

import android.os.Bundle
import android.view.Menu
import com.dhruvnagarajan.android.pubsub.PubSub
import com.dhruvnagarajan.android.pubsub.WhatNow
import com.dhruvnagarajan.android.pubsub.entity.Topic
import com.dhruvnagarajan.androidplatform.util.ext.attachObserver
import com.dhruvnagarajan.androidplatform.view.BaseActivity
import com.dhruvnagarajan.androidplatform.view.BaseViewModel
import com.dhruvnagarajan.opennews.R
import com.dhruvnagarajan.opennews.router.RootRouter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class MainActivity : BaseActivity() {

    @Inject
    lateinit var rootRouter: RootRouter

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
            rootRouter.navigateRootFeature(menuItem.itemId)
        }

//        val id = 1
//        nav.selectedItemId = id

        val pubSub = PubSub(applicationContext)

        pubSub.createTopic("locationtrack", Topic.TTL.DAY(30))
            .bridge
            .onNext(WhatNow("Test", "Hey There! 2"))

        pubSub.getTopic("locationtrack")
            .getEventUseCase.getAllEvents(WhatNow::class.java)
            .attachObserver(getBaseObserver({
                for (event in it) {
                    try {
                        val o = event
                        showError(BaseViewModel.ViewState.Error("${o.id} ${o.msg}"))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }))
    }
}
