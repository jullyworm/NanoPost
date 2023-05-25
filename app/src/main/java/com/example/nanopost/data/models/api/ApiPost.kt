package com.example.nanopost.data.models.api

@kotlinx.serialization.Serializable
data class ApiPost(
    val id: String,
    val owner: ApiProfileCompact,
    val dateCreater: Int,
    val text: String?,
    val images: List<ApiImage>,
    val  likes: ApiLike
)
