package com.example.homeapp.ui.fragments.billingedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.ui.fragments.persons.PersonsViewModel

class BillingEditViewModelFactory(
    private val appDataSource: AppDataSource,
    private val billingId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BillingEditViewModel(appDataSource, billingId) as T
    }
}