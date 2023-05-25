package com.example.nanopost.domain.auth

import com.example.nanopost.data.repository.prefs.PreferencesRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    operator fun invoke() : String? {
        return preferencesRepository.getUserId()
    }
}