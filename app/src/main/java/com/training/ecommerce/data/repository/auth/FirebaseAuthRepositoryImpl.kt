package com.training.ecommerce.data.repository.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.training.ecommerce.data.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepositoryImpl(private val auth: FirebaseAuth=FirebaseAuth.getInstance()):FirebaseAuthRepository {
    override suspend fun loginWithEmailAndPassword(email: String, password: String)
    : Flow<Resource<String>> =
        flow {

        try{
            emit(Resource.Loading())
            // suspends the coroutine until the task is complete
            val authResult = auth.signInWithEmailAndPassword(email, password).await()

            authResult.user?.let {
                Log.d(TAG, "loginWithEmailAndPassword: ${it.uid}")
                emit(Resource.Success(it.uid)) // Emit the result

            }?:run { emit(Resource.Error(Exception("User not found"))) }

        }catch (e:Exception){ emit(Resource.Error(e))}
    }

    companion object {
        private const val TAG = "FirebaseAuthRepositoryI"
    }

}