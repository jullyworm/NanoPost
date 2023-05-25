package com.example.nanopost.data.models.api

@kotlinx.serialization.Serializable
data class ApiLike(
    val liked: Boolean,
    val likesCount: Int,
)
