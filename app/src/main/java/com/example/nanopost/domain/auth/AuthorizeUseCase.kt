package com.example.nanopost.domain.auth

import com.example.nanopost.data.repository.auth.AuthRepository
import com.example.nanopost.data.models.response.TokenResponse
import javax.inject.Inject

class AuthorizeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String) : TokenResponse {
        return authRepository.authorize(username, password)
    }
}