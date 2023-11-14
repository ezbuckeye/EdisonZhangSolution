package com.fetch.codingassignment.edisonzhangsolution.model

import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getCandidates(): Flow<List<Candidate>>
    suspend fun fetchCandidates(): List<Candidate>

}