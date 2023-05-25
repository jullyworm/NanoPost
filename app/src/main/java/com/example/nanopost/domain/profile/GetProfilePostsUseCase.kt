package com.example.nanopost.domain.profile

import androidx.paging.PagingData
import com.example.nanopost.data.models.Post
import com.example.nanopost.data.repository.profile.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfilePostsUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke(profileId: String): Flow<PagingData<Post>> {
        return profileRepository.getProfilePosts(profileId)
    }
}