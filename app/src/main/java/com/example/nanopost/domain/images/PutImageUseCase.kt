package com.example.nanopost.domain.images

import com.example.nanopost.data.models.Image
import com.example.nanopost.data.models.Post
import com.example.nanopost.data.repository.images.ImagesRepository
import com.example.nanopost.data.repository.posts.PostsRepository
import javax.inject.Inject

class PutImageUseCase @Inject
constructor(
    private val imagesRepository: ImagesRepository
) {
    operator fun invoke(image: String): Image {
        return imagesRepository.putImage(image)
    }
}