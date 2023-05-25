package com.example.nanopost.domain.auth

import com.example.nanopost.data.repository.prefs.PreferencesRepository
import javax.inject.Inject

class AddUserIdUserCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    operator fun invoke(userId: String) {
        return preferencesRepository.addUserId(userId)
    }
}