package com.example.nanopost.domain.images

import com.example.nanopost.data.models.Image
import com.example.nanopost.data.repository.images.ImagesRepository
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val imagesRepository: ImagesRepository
) {
    operator fun invoke(imageId: String): Image {
        return imagesRepository.getImage(imageId)
    }
}