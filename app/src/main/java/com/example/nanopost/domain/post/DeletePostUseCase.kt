package com.example.nanopost.domain.post

import com.example.nanopost.data.repository.posts.PostsRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    operator fun invoke(postId: String) {
        return postsRepository.deletePost(postId)
    }
}