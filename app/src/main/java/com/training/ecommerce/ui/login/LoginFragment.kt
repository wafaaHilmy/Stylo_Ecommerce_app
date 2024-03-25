package com.training.ecommerce.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.training.ecommerce.R
import com.training.ecommerce.data.repository.auth.FirebaseAuthRepositoryImpl
import com.training.ecommerce.data.repository.user.UserDataStoreRepositoryImpl
import com.training.ecommerce.dataSource.datastore.UserPreferencesDataSource
import com.training.ecommerce.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
 private  lateinit var binding:FragmentLoginBinding

 private val loginViewModel: LoginViewModel by viewModels{

     LoginViewModelFactory(userPrefs=UserDataStoreRepositoryImpl(UserPreferencesDataSource(requireActivity()))
         ,authRepository=FirebaseAuthRepositoryImpl())

 }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentLoginBinding.inflate(layoutInflater,container,false)

        binding.loginViewModel=loginViewModel
        binding.lifecycleOwner=viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListners()

    }




    private fun initListners() {
        binding.signInButton.setOnClickListener {
            loginViewModel.login()
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}