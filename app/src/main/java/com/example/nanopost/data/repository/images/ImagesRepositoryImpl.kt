package com.example.nanopost.data.repository.images

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.nanopost.data.mappers.ImageMapper
import com.example.nanopost.data.models.Image
import com.example.nanopost.data.paging.ImagesPagingSource
import com.example.nanopost.data.repository.images.ImagesRepository
import com.example.nanopost.data.service.NanoPostApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(
    private val nanoPostApiService: NanoPostApiService
): ImagesRepository {
    override fun getImages(profileId: String): Flow<PagingData<Image>> {
        return Pager(
            config = PagingConfig(30, enablePlaceholders = false),
            pagingSourceFactory = { ImagesPagingSource{ count, offset ->
                nanoPostApiService.getImages(profileId, count, offset)
            }
            }
        ).flow.map{ pagingData ->
            pagingData.map{ ImageMapper.toImage(it) }
        }
    }

    override fun getImage(imageId: String): Image {
        return ImageMapper.toImage(nanoPostApiService.getImage(imageId))
    }

    override fun putImage(image: String): Image {
        return ImageMapper.toImage(nanoPostApiService.putImage(image))
    }

    override fun deleteImage(imageId: String) {
        nanoPostApiService.deleteImage(imageId)
    }

}