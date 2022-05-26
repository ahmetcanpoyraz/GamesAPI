package com.example.gamesapi.ui.bottomNavigationBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.gamesapi.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationBarActivity : AppCompatActivity() {

    private lateinit var navigationController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_bar)

        val bottomNavigationBarView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigationBarView.setupWithNavController(navController)

        navigationController = Navigation.findNavController(this,R.id.fragmentContainerView)
        NavigationUI.setupActionBarWithNavController(this,navigationController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navigationController,null)
    }
}