package com.example.homeapp.ui.fragments.persons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.ui.fragments.flats.FlatsViewModel

class PersonsViewModelFactory(
    private val appDataSource: AppDataSource
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonsViewModel(appDataSource) as T
    }
}