package com.training.ecommerce.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.training.ecommerce.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  lateinit var  binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding=ActivityMainBinding.inflate(layoutInflater)

        installSplashScreen()
        setContentView(binding.root)


    binding.textView.setOnClickListener(View.OnClickListener {
          Log.d(MainActivity::class.simpleName, "test button pressed")

          throw RuntimeException("Test Crash") // Force a crash
      })

    }
}