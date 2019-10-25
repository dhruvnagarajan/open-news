package com.dhruvnagarajan.nav

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * @author Dhruvaraj Nagarajan
 */

/*
 * activity navigation
 */
inline fun <reified T : AppCompatActivity> AppCompatActivity.startActivity(init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}

inline fun <reified T : AppCompatActivity> AppCompatActivity.startActivityForResult(
    RESULT_CODE: Int,
    init: Intent.() -> Unit
) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivityForResult(intent, RESULT_CODE)
}

inline fun <reified T : AppCompatActivity> Fragment.startActivity(
    context: Context,
    init: Intent.() -> Unit = {}
) {
    val intent = Intent(context, T::class.java)
    intent.init()
    startActivity(intent)
}

inline fun <reified T : AppCompatActivity> Fragment.startActivityForResult(
    context: Context,
    RESULT_CODE: Int,
    init: Intent.() -> Unit
) {
    val intent = Intent(context, T::class.java)
    intent.init()
    startActivityForResult(intent, RESULT_CODE)
}

// fragment navigation
fun AppCompatActivity.popFragment() {
    supportFragmentManager.popBackStack()
}

fun AppCompatActivity.popFragmentSync() {
    supportFragmentManager.popBackStackImmediate()
}

fun AppCompatActivity.addFragment(layoutId: Int, fragment: Fragment, init: Bundle.() -> Unit = {}) {
    initFragment(fragment, init)
    val transaction =
        supportFragmentManager.beginTransaction().add(layoutId, fragment, fragment.tag)
    transaction.addToBackStack(fragment.tag)
    transaction.commit()
}

fun AppCompatActivity.addFragmentToOrigin(layoutId: Int, fragment: Fragment) {
    addFragmentToPosition(layoutId, fragment, 0)
}

fun AppCompatActivity.addFragmentToPosition(
    layoutId: Int,
    fragment: Fragment,
    position: Int,
    init: Bundle.() -> Unit = {}
) {
    initFragment(fragment, init)
    val fm = supportFragmentManager
    clearBackStack(fm, position)

    addFragment(layoutId, fragment, init)
}

fun AppCompatActivity.replaceFragment(
    layoutId: Int,
    fragment: Fragment,
    init: Bundle.() -> Unit = {}
) {
    initFragment(fragment, init)

    val transaction =
        supportFragmentManager.beginTransaction().replace(layoutId, fragment, fragment.tag)
    transaction.addToBackStack(fragment.tag)
    transaction.commit()
}

fun AppCompatActivity.replaceFragmentAtOrigin(layoutId: Int, fragment: Fragment) {
    replaceFragmentAtPosition(layoutId, fragment, 0)
}

fun AppCompatActivity.replaceFragmentAtPosition(
    layoutId: Int,
    fragment: Fragment,
    position: Int,
    init: Bundle.() -> Unit = {}
) {
    initFragment(fragment, init)
    clearBackStack(supportFragmentManager, position)
    replaceFragment(layoutId, fragment, init)
}

fun initFragment(fragment: Fragment, init: Bundle.() -> Unit = {}) {
    if (init != {}) {
        val bundle = Bundle()
        bundle.init()
        fragment.arguments = bundle
    }
}

fun AppCompatActivity.showBottomSheet(
    fragment: AppCompatDialogFragment,
    init: Bundle.() -> Unit = {}
) {
    if (init != {}) {
        val bundle = Bundle()
        bundle.init()
        fragment.arguments = bundle
    }
    fragment.show(supportFragmentManager, fragment.tag)
}

fun Fragment.showBottomSheet(
    fragment: AppCompatDialogFragment,
    init: Bundle.() -> Unit = {}
) {
    if (init != {}) {
        val bundle = Bundle()
        bundle.init()
        fragment.arguments = bundle
    }
    fragment.show(childFragmentManager, fragment.tag)
}

fun AppCompatActivity.clearBackStack(fm: FragmentManager, lastInclusivePopPosition: Int) {
    val count = fm.backStackEntryCount
    if (lastInclusivePopPosition < count - 1)
        for (i in count - 1 downTo lastInclusivePopPosition) {
            fm.popBackStackImmediate()
        }
}