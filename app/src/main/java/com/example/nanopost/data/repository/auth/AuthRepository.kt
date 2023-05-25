package com.example.nanopost.data.repository.auth

import com.example.nanopost.data.models.CheckUsernameResult
import com.example.nanopost.data.models.api.ApiCheckUsernameResult
import com.example.nanopost.data.models.request.RegistrationRequest
import com.example.nanopost.data.models.response.TokenResponse

interface AuthRepository {
    suspend fun checkUsername(
        username: String
    ): CheckUsernameResult
    suspend fun authorize(
        username: String,
        password: String,
    ): TokenResponse
    suspend fun register(
        username: String,
        password: String,
    ): TokenResponse
}