package com.example.homeapp.ui.fragments.flatedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.ui.fragments.persons.PersonsViewModel

class FlatEditViewModelFactory(
    private val appDataSource: AppDataSource,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FlatEditViewModel(appDataSource) as T
    }
}