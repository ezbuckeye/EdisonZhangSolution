package com.fetch.codingassignment.edisonzhangsolution.model

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities=[Candidate::class],
    version=1
)
abstract class CandidateDatabase: RoomDatabase() {
    abstract val candidateDao: CandidateDao
}