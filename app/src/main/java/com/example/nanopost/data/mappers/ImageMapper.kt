package com.example.nanopost.data.mappers

import com.example.nanopost.data.models.api.ApiImage
import com.example.nanopost.data.models.api.ApiImageSize
import com.example.nanopost.data.models.Image
import com.example.nanopost.data.models.ImageSize
import com.example.nanopost.mapValue

object ImageMapper {
    fun toImage(apiImage: ApiImage) = Image(
        id = apiImage.id,
        owner = ProfileMapper.toProfileCompact(apiImage.owner),
        dateCreated = apiImage.dateCreated,
        sizes = apiImage.sizes.mapValue { toImageSize(it) },
    )

    fun toApiImage(image: Image) = ApiImage(
        id = image.id,
        owner = ProfileMapper.toApiProfileCompact(image.owner),
        dateCreated = image.dateCreated,
        sizes = image.sizes.mapValue { toApiImageSize(it) },
    )

    private fun toImageSize(apiImageSize: ApiImageSize) = ImageSize(
        width = apiImageSize.width,
        height = apiImageSize.height,
        url = apiImageSize.url,
    )
    private fun toApiImageSize(imageSize: ImageSize) = ApiImageSize(
        width = imageSize.width,
        height = imageSize.height,
        url = imageSize.url,
    )
}