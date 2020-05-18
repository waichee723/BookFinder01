package com.waichee.bookfinder01.search

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waichee.bookfinder01.network.BooksApi
import com.waichee.bookfinder01.network.model.BookApiResponse
import com.waichee.bookfinder01.network.model.Item
import com.waichee.bookfinder01.search.ApiStatus.DONE
import com.waichee.bookfinder01.search.ApiStatus.ERROR
import com.waichee.bookfinder01.search.ApiStatus.LOADING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, DONE, ERROR}

class SearchViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _response = MutableLiveData<BookApiResponse?>()
    val response: LiveData<BookApiResponse?>
        get() = _response


    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status


    var searchString: String = ""


    private fun getBookApiResponse(keyword: String) {
        coroutineScope.launch {
            val getBooksApiResponseDeferred = BooksApi.retrofitService.getResult(keyword)
            try {
                _status.value = LOADING
                val result = getBooksApiResponseDeferred.await()
                _status.value = DONE
                _response.value = result
                Log.i("SearchVM", result.toString())
            } catch (e: Exception) {
                _status.value = ERROR
                Log.i("SearchVM", "$e.message")
            }
        }
    }

    fun search() {
        getBookApiResponse(searchString)
        Log.i("SearchVM", "new search")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
