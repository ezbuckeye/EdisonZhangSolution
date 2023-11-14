package com.fetch.codingassignment.edisonzhangsolution.di

import com.fetch.codingassignment.edisonzhangsolution.model.MainApi
import com.fetch.codingassignment.edisonzhangsolution.model.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainApi(): MainApi {
        return Retrofit.Builder().baseUrl("https://fetch-hiring.s3.amazonaws.com/").addConverterFactory(
            GsonConverterFactory.create()).build().create(MainApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(mainApi: MainApi): MainRepository {
        return MainRepository(mainApi = mainApi)
    }

}