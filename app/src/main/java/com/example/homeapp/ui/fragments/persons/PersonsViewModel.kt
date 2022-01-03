package com.example.homeapp.ui.fragments.persons

import androidx.lifecycle.*
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.entities.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonsViewModel(
    private val appDataSource: AppDataSource
) : ViewModel() {

    private val _persons = MutableLiveData<List<Person>>(listOf())
    val persons: LiveData<List<Person>>
        get() = _persons

    fun getPersons() {
        viewModelScope.launch(Dispatchers.IO) {
            _persons.postValue(appDataSource.getAllPersons())
        }
    }

}