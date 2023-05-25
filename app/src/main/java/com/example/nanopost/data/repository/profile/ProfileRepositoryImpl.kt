package com.example.nanopost.data.repository.profile

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.nanopost.data.mappers.PostMapper
import com.example.nanopost.data.service.NanoPostApiService
import com.example.nanopost.data.models.Post
import com.example.nanopost.data.models.Profile
import com.example.nanopost.data.mappers.ProfileMapper
import com.example.nanopost.data.paging.PostPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val nanoPostApiService: NanoPostApiService
): ProfileRepository {

    override fun getProfilePosts(profileId: String): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(30, enablePlaceholders = false),
            pagingSourceFactory = {
                PostPagingSource{ count, offset ->
                    nanoPostApiService.getProfilePosts(profileId, count,offset)
                }
            }
        ).flow.map{ pagingData ->
            pagingData.map{ PostMapper.toPost(it) }
        }
    }

    override fun getProfile(id: String): Profile {
        return ProfileMapper.toProfile(nanoPostApiService.getProfile(id))
    }

    override fun subscribe(id: String) {
        nanoPostApiService.subscribe(id)
    }

    override fun unsubscribe(id: String) {
        nanoPostApiService.unsubscribe(id)
    }


}