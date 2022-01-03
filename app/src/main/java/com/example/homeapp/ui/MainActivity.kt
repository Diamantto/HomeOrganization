package com.example.homeapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.homeapp.R
import com.example.homeapp.billings
import com.example.homeapp.data.db.AppDataSource
import com.example.homeapp.data.db.OrganizerDatabase
import com.example.homeapp.databinding.ActivityHomeBinding
import com.example.homeapp.flats
import com.example.homeapp.persons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var database: AppDataSource
    private val navController: NavController by lazy { navControllerById(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigationView.setupWithNavController(navController)

//        database = AppDataSource(OrganizerDatabase.getDatabase(applicationContext).organizerDao())
//        testDatabase()
    }

    private fun navControllerById(navHostId: Int): NavController {
        return (supportFragmentManager.findFragmentById(navHostId) as NavHostFragment).navController
    }

    private fun testDatabase() {
        lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.IO) {
                flats.forEach { database.insertFlat(it) }
                persons.forEach { database.insertPerson(it) }
                billings.forEach { database.insertBilling(it) }
            }
        }
    }
}