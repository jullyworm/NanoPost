package com.example.nanopost.data.service

import com.example.nanopost.AuthClient
import com.example.nanopost.NetworkModule
import com.example.nanopost.data.models.api.ApiTokenResponse
import com.example.nanopost.data.models.request.RegistrationRequest
import com.example.nanopost.data.models.response.ObjectResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

@AuthClient
interface AuthApiService {

    @GET("/api/auth/checkUsername")
    suspend fun checkUsername(
        @Query("username") username: String,
    ): ObjectResponse

    @GET("/api/auth/login")
    suspend fun authorize(
        @Query("username") username: String,
        @Query("password") password: String,
    ): ApiTokenResponse

    @POST("/api/auth/register")
    suspend fun register(
        @Body registrationRequest: RegistrationRequest
    ): ApiTokenResponse

}