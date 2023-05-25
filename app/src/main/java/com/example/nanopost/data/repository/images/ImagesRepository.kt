package com.example.nanopost.data.repository.images

import androidx.paging.PagingData
import com.example.nanopost.data.models.Image
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    fun getImages(profileId: String): Flow<PagingData<Image>>
    fun getImage(imageId: String): Image
    fun putImage(image: String): Image
    fun deleteImage(imageId: String)
}