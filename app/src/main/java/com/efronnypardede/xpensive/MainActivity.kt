package com.efronnypardede.xpensive

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.efronnypardede.xpensive.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController: NavController
        get() = findNavController(R.id.fragment_container_main_nav_host)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
        setSupportActionBar(dataBinding.toolbar)

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_manage -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
