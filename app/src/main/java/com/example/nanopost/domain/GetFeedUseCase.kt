package com.example.nanopost.domain

import androidx.paging.PagingData
import com.example.nanopost.data.models.Post
import com.example.nanopost.data.repository.posts.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    operator fun invoke(): Flow<PagingData<Post>> {
        return postsRepository.getFeed()
    }
}