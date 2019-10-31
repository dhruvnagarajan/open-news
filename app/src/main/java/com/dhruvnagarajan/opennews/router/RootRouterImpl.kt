package com.dhruvnagarajan.opennews.router

import androidx.appcompat.app.AppCompatActivity
import com.dhruvnagarajan.androidplatform.util.ext.replaceFragment
import com.dhruvnagarajan.opennews.R
import com.dhruvnagarajan.opennews.domain.entity.NewsProfile
import com.dhruvnagarajan.opennews.view.NewsFragment
import com.dhruvnagarajan.opennews.view.ProfileFragment
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class RootRouterImpl @Inject constructor(
    val activity: AppCompatActivity
) : RootRouter {

    override fun navigateRootFeature(featureId: Int): Boolean {
        return when (featureId) {
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

    private fun openFeed() {
        val f = NewsFragment()
        f.profile = NewsProfile(q = "tesla")
        activity.replaceFragment(R.id.l_container, f)
    }

    private fun openProfile() {
        val f = ProfileFragment()
        activity.replaceFragment(R.id.l_container, f)
    }
}