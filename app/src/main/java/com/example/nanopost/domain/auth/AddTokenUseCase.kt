package com.example.nanopost.domain.auth

import com.example.nanopost.data.repository.prefs.PreferencesRepository
import javax.inject.Inject

class AddTokenUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    operator fun invoke(token: String) {
        return preferencesRepository.addToken(token)
    }
}