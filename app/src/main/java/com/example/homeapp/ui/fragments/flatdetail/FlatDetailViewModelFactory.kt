package com.example.homeapp.ui.fragments.flatdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.ui.fragments.flats.FlatsViewModel

class FlatDetailViewModelFactory(
    private val appDataSource: AppDataSource,
    private val flatId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FlatDetailViewModel(appDataSource, flatId) as T
    }
}