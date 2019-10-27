package com.dhruvnagarajan.androidplatform.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhruvnagarajan.androidplatform.R

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseEXPListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(container?.context)
            .inflate(R.layout.fragment_exp_list, container, false)
    }
}