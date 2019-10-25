package com.dhruvnagarajan.androidcore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhruvnagarajan.androidcore.R

/**
 * @author Dhruvaraj Nagarajan
 */
abstract class BaseRVListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(container?.context)
            .inflate(R.layout.fragment_rv_list, container, false)
    }
}