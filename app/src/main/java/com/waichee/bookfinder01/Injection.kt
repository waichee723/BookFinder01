package com.waichee.bookfinder01

import androidx.lifecycle.ViewModelProvider
import com.waichee.bookfinder01.network.BooksApi
import com.waichee.bookfinder01.network.BooksApiService
import com.waichee.bookfinder01.network.BooksRepository
import com.waichee.bookfinder01.search.ViewModelFactory

object Injection {
    private fun provideBooksRepository(): BooksRepository {
        return BooksRepository(BooksApi.retrofitService)
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideBooksRepository())
    }
}