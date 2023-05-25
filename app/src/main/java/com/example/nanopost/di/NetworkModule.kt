package com.example.nanopost

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.nanopost.data.service.AuthApiService
import com.example.nanopost.data.service.NanoPostApiService
import com.example.nanopost.data.repository.prefs.PreferencesRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class ApiClient
@Qualifier
annotation class AuthClient
@Qualifier
annotation class AuthInterceptor


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val BASE_URL = "https://nanopost.evolitist.com/"


    @Provides
    @Singleton
    @AuthInterceptor
    fun provideAuthInterceptor(
        preferencesRepository: PreferencesRepository
    ): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            preferencesRepository.getToken()?.let { token ->
                requestBuilder.header(
                    "Authorization",
                    "Bearer $token",
                )
            }
            val newRequest = requestBuilder.build()
            val response = chain.proceed(newRequest)
            return@Interceptor response
        }
    }

    @Provides
    @Singleton
    @ApiClient
    fun provideOkHttpClient(
        @AuthInterceptor authInterceptor: Interceptor,
        @ApplicationContext context: Context
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .addInterceptor(authInterceptor)
            .build()
    }
    @Provides
    @Singleton
    @AuthClient
    fun provideAuthRetrofit(
        moshi: Moshi
    ):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }



    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    @ApiClient
    fun provideNanoPostMRetrofit(
        @ApiClient client: OkHttpClient,
        moshi: Moshi
    ): Retrofit{
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideNanoPostApiService(
        @ApiClient retrofit: Retrofit
    ): NanoPostApiService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(
        @AuthClient retrofit: Retrofit,
    ): AuthApiService {
        return retrofit.create()
    }
}