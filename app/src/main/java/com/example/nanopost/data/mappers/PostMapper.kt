package com.example.nanopost.data.mappers

import com.example.nanopost.data.models.api.ApiLike
import com.example.nanopost.data.models.api.ApiPost
import com.example.nanopost.data.models.Like
import com.example.nanopost.data.models.Post
import com.example.nanopost.mapValue

object PostMapper {
    fun toPost(apiPost: ApiPost) = Post(
        id = apiPost.id,
        owner = ProfileMapper.toProfileCompact(apiPost.owner),
        dateCreater = apiPost.dateCreater,
        text = apiPost.text,
        images = apiPost.images.mapValue { ImageMapper.toImage(it) },
        likes = toLikes(apiPost.likes),
    )

    fun toApiPost(post: Post) = ApiPost(
        id = post.id,
        owner = ProfileMapper.toApiProfileCompact(post.owner),
        dateCreater = post.dateCreater,
        text = post.text,
        images = post.images.mapValue { ImageMapper.toApiImage(it) },
        likes = toApiLikes(post.likes),
    )

    private fun toLikes(apiLike: ApiLike) = Like(
        liked = apiLike.liked,
        likesCount = apiLike.likesCount,
    )
    private fun toApiLikes(like: Like) = ApiLike(
        liked = like.liked,
        likesCount = like.likesCount,
    )
}