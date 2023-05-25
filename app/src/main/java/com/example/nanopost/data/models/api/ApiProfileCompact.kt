package com.example.nanopost.data.models.api

@kotlinx.serialization.Serializable
data class ApiProfileCompact(
    val id: String,
    val username: String,
    val displayName: String?,
    val avatarUrl: String?,
    val subscribed: Boolean,
)
