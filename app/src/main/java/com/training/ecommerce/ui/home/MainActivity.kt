package com.training.ecommerce.ui.home

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.training.ecommerce.data.repository.user.UserDataStoreRepositoryImpl
import com.training.ecommerce.dataSource.datastore.UserPreferencesDataSource
import com.training.ecommerce.databinding.ActivityMainBinding
import com.training.ecommerce.ui.login.AuthActivity
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserDataStoreRepositoryImpl(UserPreferencesDataSource(this)))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        lifecycleScope.launch(Main) {
            val isLoggedIn = userViewModel.isUserLoggedIn()
            Log.d(TAG, "onCreate: isLoggedIn: $isLoggedIn")
            if (isLoggedIn) {
                binding = ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)

            } else {
                goToAuthActivity()
            }
        }

        binding.textView.setOnClickListener(View.OnClickListener {
            Log.d(MainActivity::class.simpleName, "test button pressed")
            throw RuntimeException("Test Crash") // Force a crash
        })

    }

    private fun goToAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val options = ActivityOptions.makeCustomAnimation(
            this, android.R.anim.fade_in, android.R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }

    companion object {
        private val TAG = MainActivity::class.simpleName
    }
}