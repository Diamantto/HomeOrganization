package com.example.homeapp.ui.fragments.billings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.entities.Billing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BillingsViewModel(
    private val appDataSource: AppDataSource
) : ViewModel() {
    private val _billings = MutableLiveData<List<Billing>>(listOf())
    val billings: LiveData<List<Billing>>
        get() = _billings

    fun getBillings() {
        viewModelScope.launch(Dispatchers.IO) {
            _billings.postValue(appDataSource.getAllBillings())
        }
    }
}