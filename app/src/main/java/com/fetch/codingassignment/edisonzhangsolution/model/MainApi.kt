package com.fetch.codingassignment.edisonzhangsolution.model

import retrofit2.Response
import retrofit2.http.GET
interface MainApi {

    @GET("hiring.json")
    suspend fun getCandidates(): Response<List<Candidate>>

}