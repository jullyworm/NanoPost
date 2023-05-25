package com.example.nanopost.data.models.api

@kotlinx.serialization.Serializable
data class ApiTokenResponse (
    val token: String,
    val userId: String,
)