package com.example.nanopost.data.models.response

import com.example.nanopost.data.models.api.ApiCheckUsernameResult
import kotlinx.serialization.Serializable

@Serializable
data class ObjectResponse(val result: ApiCheckUsernameResult)