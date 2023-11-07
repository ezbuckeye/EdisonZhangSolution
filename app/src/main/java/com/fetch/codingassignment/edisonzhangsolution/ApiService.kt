package com.fetch.codingassignment.edisonzhangsolution

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val hiringService = retrofit.create(ApiService::class.java)

interface ApiService {

    @GET("hiring.json")
    suspend fun getCandidates():List<Candidate>

}