package com.example.nanopost.data.repository.profile

import androidx.paging.PagingData
import com.example.nanopost.data.models.Post
import com.example.nanopost.data.models.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfilePosts(profileId: String): Flow<PagingData<Post>>
    fun getProfile(id: String): Profile
    fun subscribe(id: String)
    fun unsubscribe(id: String)
}