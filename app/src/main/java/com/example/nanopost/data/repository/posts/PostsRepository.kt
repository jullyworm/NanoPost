package com.example.nanopost.data.repository.posts

import androidx.paging.PagingData
import com.example.nanopost.data.models.Post
import com.example.nanopost.data.models.request.PostRequest
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getFeed(): Flow<PagingData<Post>>
    fun getPost(postId: String): Post
    fun createPost(postRequest: PostRequest)
    fun deletePost(postId: String)
}