package com.example.nanopost.domain.images

import androidx.paging.PagingData
import com.example.nanopost.data.models.Image
import com.example.nanopost.data.repository.images.ImagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val imagesRepository: ImagesRepository
) {
    operator fun invoke(profileId: String): Flow<PagingData<Image>> {
        return imagesRepository.getImages(profileId)
    }
}