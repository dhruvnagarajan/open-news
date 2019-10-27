package com.dhruvnagarajan.opennews.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhruvnagarajan.androidplatform.util.ViewModelFactory
import com.dhruvnagarajan.androidplatform.util.ext.getActivityViewModel
import com.dhruvnagarajan.androidplatform.view.BaseFragment
import com.dhruvnagarajan.opennews.R
import com.dhruvnagarajan.opennews.view.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_profile.view.*
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class ProfileFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getActivityViewModel(viewModelFactory)

        view.b_create_profile.setOnClickListener {
            openCreateProfile()
        }
    }

    private fun openCreateProfile() {
        val f = CreateProfileFragment()
        f.show(childFragmentManager, CreateProfileFragment::class.java.name)
    }
}