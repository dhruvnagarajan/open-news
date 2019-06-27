package com.opensource.news.view.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.DaggerAppCompatDialogFragment

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseBottomSheetDialogFragment : DaggerAppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(this.context!!, this.theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflateView(inflater, container)
        onCreateView(view)
        return view
    }

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View

    protected abstract fun onCreateView(view: View)
}