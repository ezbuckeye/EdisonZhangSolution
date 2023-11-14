package com.fetch.codingassignment.edisonzhangsolution.model

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidateDao {
    @Query("SELECT * FROM candidate")
    fun getCandidates(): Flow<List<Candidate>>
}