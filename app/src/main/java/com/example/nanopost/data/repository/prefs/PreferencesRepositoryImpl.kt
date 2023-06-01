package com.example.nanopost.data.repository.prefs

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : PreferencesRepository {

    companion object {
        private val KEY_ACCESS_TOKEN = "Auth"
        private val KEY_USER_TOKEN = "User"
        //private val KEY_PUSH_TOKEN = "Push"
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
    }

    override fun addToken(token: String) {
        sharedPreferences.edit {
            putString(KEY_ACCESS_TOKEN, token)
            commit()
        }
    }

    override fun deleteToken() {
        sharedPreferences.edit {
            remove(KEY_ACCESS_TOKEN)
            commit()
        }
    }

    override fun getUserId(): String? {
        return sharedPreferences.getString(KEY_USER_TOKEN, null)
    }

    override fun addUserId(userId: String) {
        sharedPreferences.edit {
            putString(KEY_USER_TOKEN, userId)
            commit()
        }
    }

    override fun deleteUserId() {
        sharedPreferences.edit {
            remove(KEY_USER_TOKEN)
            commit()
        }
    }
}