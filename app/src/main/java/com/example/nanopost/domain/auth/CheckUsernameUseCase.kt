package com.example.nanopost.domain.auth

import com.example.nanopost.data.models.CheckUsernameResult
import com.example.nanopost.data.repository.auth.AuthRepository
import javax.inject.Inject

class CheckUsernameUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String) : CheckUsernameResult {
        return authRepository.checkUsername(username)
    }
}
