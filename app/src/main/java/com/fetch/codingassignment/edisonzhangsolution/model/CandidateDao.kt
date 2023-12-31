package com.fetch.codingassignment.edisonzhangsolution.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidateDao {
    @Query("SELECT * FROM candidate WHERE name IS NOT NULL AND name != '' ORDER BY listId, id")
    fun getCandidates(): Flow<List<Candidate>>

    @Query("SELECT DISTINCT listId FROM candidate ORDER BY listId")
    fun getListIds(): Flow<List<Int>>

    @Query("DELETE FROM candidate")
    suspend fun dropCandidates()

    @Insert
    suspend fun addCandidates(candidates: List<Candidate>)

    @Query("SELECT * FROM candidate WHERE id=:id")
    suspend fun getCandidate(id: Int): Candidate
}