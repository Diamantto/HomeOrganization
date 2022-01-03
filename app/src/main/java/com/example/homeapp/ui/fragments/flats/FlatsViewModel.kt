package com.example.homeapp.ui.fragments.flats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.entities.Flat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlatsViewModel(
    private val appDataSource: AppDataSource
): ViewModel() {

    private val _flats = MutableLiveData<List<Flat>>(listOf())
    val flats: LiveData<List<Flat>>
        get() = _flats

    fun getFlats() {
        viewModelScope.launch(Dispatchers.IO) {
            _flats.postValue(appDataSource.getFlats())
        }
    }
}