package com.waichee.bookfinder01.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.waichee.bookfinder01.network.model.Item
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class BooksRepository(private val service: BooksApiService) {
    fun getSearchResult(query: String): Flow<PagingData<Item>> {
        Timber.i("getSearchResult called, new Query = $query")
        val result = Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, initialLoadSize = 10),
            pagingSourceFactory = { ItemPagingSource(service, query) }).flow

        Timber.i("Pager is %s", result.toString())

        return result
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}