package com.example.homeapp.ui.fragments.flats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homeapp.data.db.AppDataSource

class FlatsViewModelFactory(
    private val appDataSource: AppDataSource
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FlatsViewModel(appDataSource) as T
    }
}