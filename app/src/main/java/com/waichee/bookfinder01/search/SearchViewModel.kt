package com.waichee.bookfinder01.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.waichee.bookfinder01.network.BooksRepository
import com.waichee.bookfinder01.network.model.BookApiResponse
import com.waichee.bookfinder01.network.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber

enum class ApiStatus { LOADING, DONE, ERROR }

@ExperimentalCoroutinesApi
class SearchViewModel(private val repository: BooksRepository): ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Item>>? = null

    fun search(queryString: String): Flow<PagingData<Item>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Item>> = repository.getSearchResult(queryString).cachedIn(coroutineScope)
        currentSearchResult = newResult
        return newResult
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
