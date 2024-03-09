package com.training.ecommerce.data.repository.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.training.ecommerce.dataSource.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.training.ecommerce.dataSource.datastore.DataStoreKeys.USER_ID
import com.training.ecommerce.dataSource.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferenceRepositoryImp(private var context: Context):UserPreferenceRepository{
    override suspend fun isUserLoggedIn(): Flow<Boolean> {
   return context.dataStore.data.map {
       it[IS_USER_LOGGED_IN]?:false
    }
    }

    override suspend fun saveLoginState(isLoggedIn: Boolean) {
        context.dataStore.edit {
            it[IS_USER_LOGGED_IN]=isLoggedIn
        }
    }

    override suspend fun saveUserID(userId: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] =userId
        }
    }
}