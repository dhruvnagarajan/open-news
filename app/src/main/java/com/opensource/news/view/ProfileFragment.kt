package com.opensource.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhruvnagarajan.androidcore.util.ViewModelFactory
import com.dhruvnagarajan.androidcore.util.ext.getActivityViewModel
import com.dhruvnagarajan.androidcore.view.BaseFragment
import com.opensource.news.R
import com.opensource.news.view.main.MainViewModel
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