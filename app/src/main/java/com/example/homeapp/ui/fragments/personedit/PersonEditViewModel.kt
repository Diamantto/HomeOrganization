package com.example.homeapp.ui.fragments.personedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.entities.Flat
import com.example.homeapp.data.db.entities.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonEditViewModel(
    private val appDataSource: AppDataSource,
    private val personId: Int
) : ViewModel() {

    private val _insertionPersonState = MutableLiveData<Boolean>()
    val insertionPersonState: LiveData<Boolean>
        get() = _insertionPersonState

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person>
        get() = _person

    fun insertPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            _insertionPersonState.postValue(appDataSource.insertPerson(person))
        }
    }

    fun deletePerson(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _insertionPersonState.postValue(appDataSource.deletePerson(id))
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _person.postValue(appDataSource.getPerson(personId))
        }
    }
}