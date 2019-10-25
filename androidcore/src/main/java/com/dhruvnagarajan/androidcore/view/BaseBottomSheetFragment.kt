package com.dhruvnagarajan.androidcore.view

import android.app.Dialog
import android.os.Bundle

import com.google.android.material.bottomsheet.BottomSheetDialog

import dagger.android.support.DaggerAppCompatDialogFragment

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseBottomSheetFragment : DaggerAppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(context!!, theme)
}

