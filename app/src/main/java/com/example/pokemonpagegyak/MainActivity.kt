package com.example.pokemonpagegyak

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.pokemonpagegyak.databinding.ActivityMainBinding
import com.example.pokemonpagegyak.ui.fragmentDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var NavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* val fargment= MainPageFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_main,fargment)
            commit()
        }*/

       val navhost = supportFragmentManager.findFragmentById(R.id.fragment_container_main)
        if (navhost != null) {
            NavController = navhost.findNavController()
        }
        val appBarController = AppBarConfiguration(NavController.graph)
        setupActionBarWithNavController(NavController, appBarController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavController.navigateUp()||super.onSupportNavigateUp()
    }
}