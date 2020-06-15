package com.waichee.bookfinder01.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.waichee.bookfinder01.Injection
import com.waichee.bookfinder01.databinding.FragmentSearchBinding
import com.waichee.bookfinder01.network.BooksRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private var searchJob: Job? = null
    private val adapter = BookListAdapter()

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.search(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater)

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
            .get(SearchViewModel::class.java)


        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        binding.recyclerViewBookList.adapter = adapter

        search("harry")



        return binding.root
    }


}