package com.example.nanopost.domain.post

import com.example.nanopost.data.models.Post
import com.example.nanopost.data.repository.posts.PostsRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    operator fun invoke(postId: String): Post {
        return postsRepository.getPost(postId)
    }
}