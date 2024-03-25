package com.training.ecommerce.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.training.ecommerce.data.models.Resource
import com.training.ecommerce.data.repository.auth.FirebaseAuthRepository
import com.training.ecommerce.data.repository.user.UserPreferenceRepository
import com.training.ecommerce.utils.isValidEmail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginViewModel(private val userPrefs: UserPreferenceRepository,
                     private val authRepository: FirebaseAuthRepository):ViewModel() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val isLoginIsValid: Flow<Boolean> = combine(email, password) { email, password ->
        email.isValidEmail() && password.length >= 6
    }

    fun login()=viewModelScope.launch {
        val email = email.value
        val password = password.value
        if(isLoginIsValid.first()){
            authRepository.loginWithEmailAndPassword(email,password).onEach {resource ->

//                    when(resource){
//                        is Resource.Success ->userPrefs.saveUserEmail(email)
//                    }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}

// create viewmodel factory class
class LoginViewModelFactory(
    private val userPrefs: UserPreferenceRepository,
    private val authRepository: FirebaseAuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return LoginViewModel(userPrefs, authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}