package com.example.nanopost.domain.post

import com.example.nanopost.data.models.request.PostRequest
import com.example.nanopost.data.repository.posts.PostsRepository
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    operator fun invoke(
        text: String?,
        image1: String?,
        image2: String?,
        image3: String?,
        image4: String?,
    ) {
        return postsRepository.createPost(PostRequest(text, image1, image2, image3, image4))
    }
}