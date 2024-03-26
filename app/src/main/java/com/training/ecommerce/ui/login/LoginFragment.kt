package com.training.ecommerce.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.training.ecommerce.R
import com.training.ecommerce.data.models.Resource
import com.training.ecommerce.data.repository.auth.FirebaseAuthRepositoryImpl
import com.training.ecommerce.data.repository.user.UserDataStoreRepositoryImpl
import com.training.ecommerce.dataSource.datastore.UserPreferencesDataSource
import com.training.ecommerce.databinding.FragmentLoginBinding
import com.training.ecommerce.ui.views.ProgressDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
 private  lateinit var binding:FragmentLoginBinding
    private val progressDialog by lazy { ProgressDialog.createProgressDialog(requireActivity()) }

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

        initListeners()
        initViewModel()

    }

    private fun initViewModel() {
        lifecycleScope.launch {
            loginViewModel.loginState.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        progressDialog.show()
                    }

                    is Resource.Success -> {
                        progressDialog.dismiss()
                        Toast.makeText(requireActivity(),"login successes",Toast.LENGTH_SHORT).show()
                        //go to main activity

                    }

                    is Resource.Error -> {
                        progressDialog.dismiss()
                        val msg = resource.exception?.message ?: getString(R.string.generic_err_msg)
                       Toast.makeText(requireActivity(),msg,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.signInButton.setOnClickListener {
            loginViewModel.login()
        }

        binding.registerTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}