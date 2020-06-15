package com.waichee.bookfinder01.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.waichee.bookfinder01.network.model.Item
import kotlinx.coroutines.flow.Flow

class BooksRepository(private val service: BooksApiService) {
    fun getSearchResult(query: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                initialLoadSize = 10),
            pagingSourceFactory = { ItemPagingSource(service, query) }).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}