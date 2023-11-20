package com.fetch.codingassignment.edisonzhangsolution.model

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MainRepository {
    fun getCandidates(): Flow<List<Candidate>>

    fun getListIds(): Flow<List<Int>>
    suspend fun fetchCandidates(): Response<List<Candidate>>

    suspend fun getCandidateDetail(id: Int): Candidate

}