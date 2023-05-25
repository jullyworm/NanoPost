package com.example.nanopost.data.models.api

@kotlinx.serialization.Serializable
data class ApiImage(
    val id: String,
    val owner: ApiProfileCompact,
    val dateCreated: String,
    val sizes: List<ApiImageSize>
)
