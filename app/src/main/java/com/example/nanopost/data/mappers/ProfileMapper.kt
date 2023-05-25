package com.example.nanopost.data.mappers

import com.example.nanopost.data.models.api.ApiProfile
import com.example.nanopost.data.models.api.ApiProfileCompact
import com.example.nanopost.data.models.Profile
import com.example.nanopost.data.models.ProfileCompact
import com.example.nanopost.mapValue

object ProfileMapper {
    fun toProfile(apiProfile: ApiProfile) = Profile(
        id = apiProfile.id,
        username = apiProfile.username,
        displayName = apiProfile.displayName,
        bio = apiProfile.bio,
        avatarId = apiProfile.avatarId,
        avatarSmall = apiProfile.avatarSmall,
        avatarLarge = apiProfile.avatarLarge,
        subscribed = apiProfile.subscribed,
        subscribersCount = apiProfile.subscribersCount,
        postsCount = apiProfile.postsCount,
        imagesCount = apiProfile.imagesCount,
        images = apiProfile.images.mapValue { ImageMapper.toImage(it) },
    )

    fun toApiProfile(profile: Profile) = ApiProfile(
        id = profile.id,
        username = profile.username,
        displayName = profile.displayName,
        bio = profile.bio,
        avatarId = profile.avatarId,
        avatarSmall = profile.avatarSmall,
        avatarLarge = profile.avatarLarge,
        subscribed = profile.subscribed,
        subscribersCount = profile.subscribersCount,
        postsCount = profile.postsCount,
        imagesCount = profile.imagesCount,
        images = profile.images.mapValue { ImageMapper.toApiImage(it) },
    )

    fun toProfileCompact(apiProfileCompact: ApiProfileCompact) = ProfileCompact(
        id = apiProfileCompact.id,
        username = apiProfileCompact.username,
        displayName = apiProfileCompact.displayName,
        avatarUrl = apiProfileCompact.avatarUrl,
        subscribed = apiProfileCompact.subscribed,
    )
    fun toApiProfileCompact(profileCompact: ProfileCompact) = ApiProfileCompact(
        id = profileCompact.id,
        username = profileCompact.username,
        displayName = profileCompact.displayName,
        avatarUrl = profileCompact.avatarUrl,
        subscribed = profileCompact.subscribed,
    )
}