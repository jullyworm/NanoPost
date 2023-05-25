package com.example.nanopost.data.service

import com.example.nanopost.data.models.api.ApiImage
import com.example.nanopost.data.models.api.ApiPost
import com.example.nanopost.data.models.api.ApiProfile
import com.example.nanopost.data.models.response.PagedDataResponse
import com.example.nanopost.data.models.request.PostRequest
import com.example.nanopost.data.models.response.ResultResponse
import retrofit2.http.*

interface NanoPostApiService {

    @GET("/api/v1/profile/{profileId}")
    fun getProfile(
        @Path("profileId") profileId: String
    ): ApiProfile

    @GET("/api/v1/posts/{profileId}")
    fun getProfilePosts(
        @Path("profileId") profileId: String,
        @Query("count") count: Int,
        @Query("offset") offset: String?,
    ): PagedDataResponse<ApiPost>


    @PATCH("/api/v1/profile/{profileId}")
    fun putProfile(
        @Path("profileId") profileId: String,
        @Query("displayName") displayName: String?,
        @Query("bio") bio: String?,
        @Query("avatar") avatar: String?,
    ): ResultResponse

    @PUT("/api/v1/profile/{profileId}/subscribe")
    fun subscribe(
        @Path("profileId") profileId: String,
    ): ResultResponse

    @PUT("/api/v1/profile/{profileId}/unsubscribe")
    fun unsubscribe(
        @Path("profileId") profileId: String,
    ): ResultResponse

    @GET("/api/v1/feed")
    fun getFeed(
        @Query("count") count: Int,
        @Query("offset") offset: String?,
    ): PagedDataResponse<ApiPost>

    @PUT("/api/v1/post")
    fun createPost(
        @Body postRequest: PostRequest,
    ): ApiPost

    @GET("/api/v1/post/{postId}")
    fun getPost(
        @Path("postId") postId: String
    ): ApiPost

    @DELETE("/api/v1/post/{postId}")
    fun deletePost(
        @Query("postId") postId: String,
    ): ResultResponse

    @PUT("/api/v1/profile/pushToken")
    fun setPushToken(
        @Query("pushToken") pushToken: String,
    ): ResultResponse

    @DELETE("/api/v1/profile/pushToken")
    fun deletePushToken(
        @Query("pushToken") pushToken: String,
    ): ResultResponse

    @GET("/api/v1/images/{profileId}")
    fun getImages(
        @Path("profileId") profileId: String,
        @Query("count") count: Int,
        @Query("offset") offset: String?,
    ): PagedDataResponse<ApiImage>

    @PUT("/api/v1/image")
    fun putImage(
        @Query("image") image: String,
    ): ApiImage

    @GET("/api/v1/image/{imageId}")
    fun getImage(
        @Path("imageId") imageId: String
    ): ApiImage

    @DELETE("/api/v1/image/{imageId}")
    fun deleteImage(
        @Path("imageId") imageId: String
    ): ResultResponse

    /*("posts/{profileId}?count=20&")*/
}