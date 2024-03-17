package com.training.ecommerce.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.training.ecommerce.data.repository.user.UserPreferenceRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserViewModel(private val userPreferencesRepository: UserPreferenceRepository):ViewModel() {

    suspend fun isUserLoggedIn() = userPreferencesRepository.isUserLoggedIn().first()

    fun setIsLoggedIn(b: Boolean) {
        viewModelScope.launch(IO) {
            userPreferencesRepository.saveLoginState(b)
        }
    }

}
/******************************************************************************************************/
class UserViewModelFactory(private val userPreferencesRepository: UserPreferenceRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return UserViewModel(userPreferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}