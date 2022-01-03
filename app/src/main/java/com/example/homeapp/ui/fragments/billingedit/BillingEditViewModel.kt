package com.example.homeapp.ui.fragments.billingedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.entities.Billing
import com.example.homeapp.data.db.entities.Month
import com.example.homeapp.data.db.entities.Person
import com.example.homeapp.data.db.entities.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BillingEditViewModel(
    private val appDataSource: AppDataSource,
    private val billingId: Int
) : ViewModel() {

    val billingMonth: List<String> = Month.values().map { it.name }.toList()
    val billingService: List<String> = Service.values().map { it.name }.toList()

    private val _insertionBillingState = MutableLiveData<Boolean>()
    val insertionBillingState: LiveData<Boolean>
        get() = _insertionBillingState

    private val _billing = MutableLiveData<Billing>()
    val billing: LiveData<Billing>
        get() = _billing

    fun insertBilling(billing: Billing) {
        viewModelScope.launch(Dispatchers.IO) {
            _insertionBillingState.postValue(appDataSource.insertBilling(billing))
        }
    }

    fun deleteBilling(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _insertionBillingState.postValue(appDataSource.deleteBilling(id))
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _billing.postValue(appDataSource.getBilling(billingId))
        }
    }
}