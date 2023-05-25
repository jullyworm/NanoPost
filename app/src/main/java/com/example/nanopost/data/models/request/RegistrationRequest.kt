package com.example.nanopost.data.models.request

@kotlinx.serialization.Serializable
data class RegistrationRequest(
    val username:String,
    val password:String,
)