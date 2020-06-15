package com.waichee.bookfinder01.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.waichee.bookfinder01.Injection
import com.waichee.bookfinder01.databinding.FragmentSearchBinding
import com.waichee.bookfinder01.network.BooksRepository
import com.waichee.bookfinder01.toVisibility
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    @ExperimentalCoroutinesApi
    private lateinit var viewModel: SearchViewModel
    private var searchJob: Job? = null
    private val adapter = BookListAdapter()

    @ExperimentalCoroutinesApi
    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.search(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater)

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
            .get(SearchViewModel::class.java)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerViewBookList.adapter = adapter

        binding.searchButton.setOnClickListener {
            binding.recyclerViewBookList.scrollToPosition(0)
            binding.searchInput.text.trim().let {
                if (it.isNotEmpty()) {
                    search(it.toString())
                }
            }
        }

        adapter.addLoadStateListener {loadStates ->
            if (loadStates.refresh !is LoadState.NotLoading) {
                // is Loading
                binding.recyclerViewBookList.visibility = View.GONE
                binding.progressBar.visibility = toVisibility(loadStates.refresh is LoadState.Loading)
                binding.retryButton.visibility = toVisibility(loadStates.refresh is LoadState.Error)
            } else {
                // not loading
                binding.recyclerViewBookList.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.retryButton.visibility = View.GONE

                val errorState = when {
                    loadStates.append is LoadState.Error -> {
                        loadStates.append as LoadState.Error
                    }
                    loadStates.prepend is LoadState.Error -> {
                        loadStates.prepend as LoadState.Error
                    }
                    else -> {
                        null
                    }
                }
                errorState?.let {
                    Toast.makeText(
                        this.context,
                        "\uD83D\uDE28 Wooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }


        return binding.root
    }


}