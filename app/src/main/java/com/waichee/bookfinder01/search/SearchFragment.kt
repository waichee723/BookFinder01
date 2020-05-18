package com.waichee.bookfinder01.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.waichee.bookfinder01.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        val adapter = BookListAdapter()
        binding.recyclerViewBookList.adapter = adapter

//        viewModel.response.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                adapter.submitList(it.items)
//            }
//        })



        return binding.root
    }
}