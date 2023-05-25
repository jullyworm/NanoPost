package com.example.nanopost.domain.profile

import com.example.nanopost.data.models.Profile
import com.example.nanopost.data.repository.profile.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke(profileId: String): Profile {
        return profileRepository.getProfile(profileId)
    }
}