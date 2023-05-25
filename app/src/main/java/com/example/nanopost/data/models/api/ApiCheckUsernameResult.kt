package com.example.nanopost.data.models.api

@kotlinx.serialization.Serializable
enum class ApiCheckUsernameResult{
    TooShort,
    TooLong,
    InvalidCharacters,
    Taken,
    Free,
}