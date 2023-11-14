package com.fetch.codingassignment.edisonzhangsolution

import com.fetch.codingassignment.edisonzhangsolution.model.Candidate
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
interface ApiService {

    @GET("hiring.json")
    suspend fun getCandidates():List<Candidate>

}