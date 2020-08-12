package io.github.occultus73.poqrepositories.framework.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.occultus73.poqrepositories.R
import io.github.occultus73.poqrepositories.business.domain.state.DataState
import io.github.occultus73.poqrepositories.framework.presentation.state.MainStateEvent
import io.github.occultus73.poqrepositories.framework.presentation.state.MainStateEvent.*
import io.github.occultus73.poqrepositories.framework.presentation.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment: Fragment(R.layout.fragment_main){
    companion object {
        private const val TAG = "MainFragment"
    }

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var rvAdapter: RVAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        subscribeObservers()
        viewModel.setStateEvent(GetSquareReposEvent)
    }

    private fun setupRecyclerView() {
        rv_square_repos_item.adapter = rvAdapter
        rv_square_repos_item.layoutManager = LinearLayoutManager(context)
        rv_square_repos_item.setHasFixedSize(true)
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { mainViewState ->
            rvAdapter.submitList(mainViewState.squareReposItems)
        })

        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success -> {
                    displayProgressBar(false)
                    viewModel.setViewState(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    Log.e(TAG, "subscribeObservers: ", dataState.exception)
                    Toast.makeText(context, dataState.exception.message, Toast.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }
}