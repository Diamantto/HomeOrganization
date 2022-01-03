package com.example.homeapp.ui.fragments.personedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.ui.fragments.persons.PersonsViewModel

class PersonEditViewModelFactory(
    private val appDataSource: AppDataSource,
    private val personId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonEditViewModel(appDataSource, personId) as T
    }
}