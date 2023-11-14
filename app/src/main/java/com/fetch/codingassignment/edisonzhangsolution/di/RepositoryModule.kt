package com.fetch.codingassignment.edisonzhangsolution.di

import com.fetch.codingassignment.edisonzhangsolution.model.MainRepository
import com.fetch.codingassignment.edisonzhangsolution.model.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository

}