package com.fetch.codingassignment.edisonzhangsolution.model

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MainRepository {
    fun getCandidates(): Flow<List<Candidate>>
    suspend fun fetchCandidates(): Response<List<Candidate>>

}