package com.training.ecommerce.data.repository.auth

import com.training.ecommerce.data.models.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    suspend fun loginWithEmailAndPassword(email: String, password: String): Flow<Resource<String>>
}