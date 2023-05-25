package com.example.nanopost.data.repository.posts

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.nanopost.data.service.NanoPostApiService
import com.example.nanopost.data.models.Post
import com.example.nanopost.data.mappers.PostMapper
import com.example.nanopost.data.models.request.PostRequest
import com.example.nanopost.data.paging.PostPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val nanoPostApiService: NanoPostApiService
): PostsRepository {

    override fun getFeed(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(30, enablePlaceholders = false),
            pagingSourceFactory = { PostPagingSource{ count, offset ->
                nanoPostApiService.getFeed(count,offset)
            }
            }
        ).flow.map{ pagingData ->
            pagingData.map{ PostMapper.toPost(it) }
        }
    }

    override fun getPost(postId: String): Post {
        return PostMapper.toPost(nanoPostApiService.getPost(postId))
    }

    override fun createPost(postRequest: PostRequest) {
        nanoPostApiService.createPost(postRequest)
    }

    override fun deletePost(postId: String) {
        nanoPostApiService.deletePost(postId)
    }
}