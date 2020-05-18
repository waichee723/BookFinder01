package com.waichee.bookfinder01.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartViewModel : ViewModel() {

    private val _navigateToSearch = MutableLiveData<Boolean?>()
    val navigateToSearch: LiveData<Boolean?>
        get() = _navigateToSearch

    fun displaySearch() {
        _navigateToSearch.value = true
    }

    fun displaySearchComplete() {
        _navigateToSearch.value = null
    }

}