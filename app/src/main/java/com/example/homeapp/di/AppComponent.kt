package com.example.homeapp.di

import com.example.homeapp.ui.fragments.billingedit.BillingEditFragment
import com.example.homeapp.ui.fragments.billings.BillingsFragment
import com.example.homeapp.ui.fragments.flatdetail.FlatDetailFragment
import com.example.homeapp.ui.fragments.flatedit.FlatEditFragment
import com.example.homeapp.ui.fragments.flats.FlatsFragment
import com.example.homeapp.ui.fragments.personedit.PersonEditFragment
import com.example.homeapp.ui.fragments.persons.PersonsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface AppComponent {
    fun inject(fragment: FlatsFragment)
    fun inject(fragment: BillingsFragment)
    fun inject(fragment: PersonsFragment)
    fun inject(fragment: FlatDetailFragment)
    fun inject(fragment: PersonEditFragment)
    fun inject(fragment: BillingEditFragment)
    fun inject(fragment: FlatEditFragment)
}