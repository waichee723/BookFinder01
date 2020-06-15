package com.waichee.bookfinder01.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.waichee.bookfinder01.network.BooksRepository

class ViewModelFactory(private val repository: BooksRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}