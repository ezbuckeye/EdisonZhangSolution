package com.fetch.codingassignment.edisonzhangsolution.di

import android.app.Application
import androidx.room.Room
import com.fetch.codingassignment.edisonzhangsolution.model.CandidateDao
import com.fetch.codingassignment.edisonzhangsolution.model.CandidateDatabase
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
    fun provideCandidateDao(app: Application): CandidateDao {
        return Room.databaseBuilder(
            app,
            CandidateDatabase::class.java,
            "candidate_db"
        ).build().candidateDao
    }

    @Provides
    @Singleton
    fun provideMainApi(): MainApi {
        return Retrofit.Builder().baseUrl("https://fetch-hiring.s3.amazonaws.com/").addConverterFactory(
            GsonConverterFactory.create()).build().create(MainApi::class.java)
    }

}