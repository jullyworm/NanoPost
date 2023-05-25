package com.example.nanopost.data.models.response

@kotlinx.serialization.Serializable
data class PagedDataResponse<T>(
    val count: Int,
    val total: Int,
    val offset: String? = null,
    val items: List<T>,
)
