package com.opensource.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhruvnagarajan.androidplatform.util.ViewModelFactory
import com.dhruvnagarajan.androidplatform.util.ext.attachObserver
import com.dhruvnagarajan.androidplatform.util.ext.getActivityViewModel
import com.dhruvnagarajan.androidplatform.view.BaseBottomSheetFragment
import com.opensource.news.R
import com.opensource.news.domain.entity.NewsProfile
import com.opensource.news.view.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_profile_create.*
import kotlinx.android.synthetic.main.fragment_profile_create.view.*
import javax.inject.Inject

/**
 * @author Dhruvaraj Nagarajan
 */
class CreateProfileFragment : BaseBottomSheetFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getActivityViewModel(viewModelFactory)

        view.b_create_profile.setOnClickListener {
            val sources = et_sources.text.toString()
            val q = et_query.text.toString()
            val language = et_language.text.toString()
            val country = et_county.text.toString()
            val category = et_category.text.toString()

            if (sources.isNullOrEmpty() &&
                q.isNullOrEmpty() &&
                language.isNullOrEmpty() &&
                country.isNullOrEmpty() &&
                category.isNullOrEmpty()
            ) {
                postError("At least one of the fields is required.")
                return@setOnClickListener
            }

            val profile = NewsProfile(sources, q, language, country, category)

            viewModel.createNewsProfile(profile).attachObserver(getBaseObserver({
                postSuccess("Profile Created.")
                dismiss()
            }))
        }
    }
}