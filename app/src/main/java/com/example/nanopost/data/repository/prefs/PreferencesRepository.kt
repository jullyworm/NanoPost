package com.example.nanopost.data.repository.prefs

interface PreferencesRepository {
    fun getToken(): String?
    fun addToken(token: String)
    fun deleteToken()
    fun getUserId(): String?
    fun addUserId(userId: String)
    fun deleteUserId()
}