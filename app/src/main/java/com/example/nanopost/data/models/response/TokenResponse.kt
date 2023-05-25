package com.example.nanopost.data.models.response

@kotlinx.serialization.Serializable
data class TokenResponse (
    val token: String,
    val userId: String,
)
