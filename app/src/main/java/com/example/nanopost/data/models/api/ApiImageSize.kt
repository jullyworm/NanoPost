package com.example.nanopost.data.models.api

@kotlinx.serialization.Serializable
data class ApiImageSize(
    val width: Int,
    val height: Int,
    val url: String,
)
