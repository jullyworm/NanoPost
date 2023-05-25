package com.example.nanopost.data.repository.auth

import com.example.nanopost.data.mappers.CheckUsernameMapper
import com.example.nanopost.data.mappers.TokenMapper
import com.example.nanopost.data.models.CheckUsernameResult
import com.example.nanopost.data.models.request.RegistrationRequest
import com.example.nanopost.data.models.response.TokenResponse
import com.example.nanopost.data.repository.auth.AuthRepository
import com.example.nanopost.data.service.AuthApiService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
): AuthRepository {

    override suspend fun checkUsername(username: String): CheckUsernameResult {
        return CheckUsernameMapper.toModel(authApiService.checkUsername(username).result)
    }

    override suspend fun authorize(username: String, password: String): TokenResponse {
        return TokenMapper.toModel(authApiService.authorize(username, password))
    }

    override suspend fun register(username: String, password: String): TokenResponse {
        return TokenMapper.toModel(authApiService.register(
            RegistrationRequest(
                username,
                password,
            )
        ))
    }

}