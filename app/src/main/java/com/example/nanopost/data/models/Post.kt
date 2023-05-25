package com.example.nanopost.data.models

data class Post(
    val id: String,
    val owner: ProfileCompact,
    val dateCreater: Int,
    val text: String?,
    val images: List<Image>,
    val likes: Like
)
