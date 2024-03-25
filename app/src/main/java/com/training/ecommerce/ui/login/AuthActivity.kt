package com.training.ecommerce.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.training.ecommerce.R
import com.training.ecommerce.databinding.ActivityAuthBinding


class AuthActivity : AppCompatActivity() {

   private lateinit var binding: ActivityAuthBinding
  // private lateinit var loginViewModel: LoginViewModel
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // loginViewModel=ViewModelProvider(this).get(LoginViewModel::class.java)
        // Obtain reference to the NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.login_nav_host_fragment) as NavHostFragment
        // Get the NavController
        navController = navHostFragment.navController
        // Set up the ActionBar with the Navigation UI


    }
}