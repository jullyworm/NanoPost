package com.example.nanopost.di

import com.example.nanopost.data.repository.*
import com.example.nanopost.data.repository.auth.AuthRepository
import com.example.nanopost.data.repository.auth.AuthRepositoryImpl
import com.example.nanopost.data.repository.images.ImagesRepository
import com.example.nanopost.data.repository.images.ImagesRepositoryImpl
import com.example.nanopost.data.repository.posts.PostsRepository
import com.example.nanopost.data.repository.posts.PostsRepositoryImpl
import com.example.nanopost.data.repository.prefs.PreferencesRepository
import com.example.nanopost.data.repository.prefs.PreferencesRepositoryImpl
import com.example.nanopost.data.repository.profile.ProfileRepository
import com.example.nanopost.data.repository.profile.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindPreferencesRepository(
        impl: PreferencesRepositoryImpl
    ): PreferencesRepository

    @Binds
    fun bindProfileRepository(
        impl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    fun bindPostsRepository(
        impl: PostsRepositoryImpl
    ): PostsRepository

    @Binds
    fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindImagesRepository(
        impl: ImagesRepositoryImpl
    ): ImagesRepository
}