package com.example.homeapp.ui.fragments.flatedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlatEditViewModel(
    private val appDataSource: AppDataSource,
) : ViewModel() {

    private val _insertionFlatState = MutableLiveData<Boolean>()
    val insertionFlatState: LiveData<Boolean>
        get() = _insertionFlatState

    fun insertFlat(flat: Flat) {
        viewModelScope.launch(Dispatchers.IO) {
            _insertionFlatState.postValue(appDataSource.insertFlat(flat))
        }
    }

    fun deleteFlat(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _insertionFlatState.postValue(appDataSource.deleteFlat(id))
        }
    }
}