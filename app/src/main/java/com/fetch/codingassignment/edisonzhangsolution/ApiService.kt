package com.fetch.codingassignment.edisonzhangsolution

import com.fetch.codingassignment.edisonzhangsolution.model.Candidate
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
interface ApiService {

    @GET("hiring.json")
    suspend fun getCandidates():List<Candidate>


    companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }

    }

}