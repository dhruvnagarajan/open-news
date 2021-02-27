package com.opensource.news.view.configure

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opensource.news.R
import com.opensource.news.domain.usecase.GetTopHeadlinesUseCase
import com.opensource.news.util.ViewModelFactory
import com.opensource.news.view.base.BaseBottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_configuretopic.view.*
import javax.inject.Inject

/**
 * @author dhruvaraj
 */
class ConfigureTopicBottomSheet : BaseBottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var onConfigSaved: () -> Unit
    private lateinit var viewModel: ConfigurationViewModel

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View =
        inflater.inflate(R.layout.bottomsheet_configuretopic, container, false)

    override fun onCreateView(view: View) {
        viewModel = ViewModelProviders.of(
            requireActivity(),
            viewModelFactory
        )[ConfigurationViewModel::class.java]

        view.run {
            b_save.setOnClickListener {
                viewModel.saveConfiguration(
                    GetTopHeadlinesUseCase.Params(
                        sources = et_sources.text?.toString(),
                        q = et_query.text?.toString(),
                        language = et_language.text?.toString(),
                        country = et_country.text?.toString(),
                        category = et_category.text?.toString()
                    )
                ).observe(viewLifecycleOwner, Observer {
                    if (it.isLoading) {

                    } else if (it.data != null) {
                        Toast.makeText(requireContext(), it.data, Toast.LENGTH_SHORT).show()
                        dismiss()
                        onConfigSaved()
                    } else {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
            iv_close.setOnClickListener {
                dismiss()
            }
        }

        viewModel.getConfiguration().observe(viewLifecycleOwner, Observer {
            if (it.isLoading) {

            } else if (it.data != null) {
                val config = it.data
                view.run {
                    et_query.setText(config.q)
                    et_sources.setText(config.sources)
                    et_language.setText(config.language)
                    et_country.setText(config.country)
                    et_category.setText(config.category)
                }
            } else {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}