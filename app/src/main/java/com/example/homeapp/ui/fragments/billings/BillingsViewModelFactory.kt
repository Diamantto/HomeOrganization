package com.example.homeapp.ui.fragments.billings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homeapp.data.db.AppDataSource

class BillingsViewModelFactory(
    private val appDataSource: AppDataSource
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BillingsViewModel(appDataSource) as T
    }
}