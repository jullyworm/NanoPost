package com.example.nanopost.domain.auth

import com.example.nanopost.data.repository.prefs.PreferencesRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    operator fun invoke() : String? {
        return preferencesRepository.getToken()
    }
}