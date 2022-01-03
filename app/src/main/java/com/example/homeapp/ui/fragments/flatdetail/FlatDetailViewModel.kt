package com.example.homeapp.ui.fragments.flatdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.entities.Billing
import com.example.homeapp.data.db.entities.Flat
import com.example.homeapp.data.db.entities.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlatDetailViewModel(
    private val appDataSource: AppDataSource,
    private val flatId: Int
) : ViewModel() {

    private val _billings = MutableLiveData<List<Billing>>(listOf())
    val billings: LiveData<List<Billing>>
        get() = _billings

    private val _persons = MutableLiveData<List<Person>>(listOf())
    val persons: LiveData<List<Person>>
        get() = _persons

    private val _flat = MutableLiveData<Flat>()
    val flat: LiveData<Flat>
        get() = _flat

    fun getBillings(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _billings.postValue(appDataSource.getFlatWithBillings(id)?.billing)
        }
    }

    fun getPersons(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _persons.postValue(appDataSource.getFlatWithPersons(id)?.persons)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _flat.postValue(appDataSource.getFlat(flatId))
        }
    }
}