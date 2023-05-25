package com.example.nanopost.data.mappers

import com.example.nanopost.data.models.Image
import com.example.nanopost.data.models.api.ApiImage
import com.example.nanopost.data.models.api.ApiTokenResponse
import com.example.nanopost.data.models.response.TokenResponse
import com.example.nanopost.mapValue

object TokenMapper {

    fun toModel(apiTokenResponse: ApiTokenResponse) = TokenResponse(
        token = apiTokenResponse.token,
        userId = apiTokenResponse.userId
    )
}