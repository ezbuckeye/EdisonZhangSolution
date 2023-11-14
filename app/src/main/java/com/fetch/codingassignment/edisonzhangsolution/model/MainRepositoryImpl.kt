package com.fetch.codingassignment.edisonzhangsolution.model

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val mainApi: MainApi, private val dao: CandidateDao) : MainRepository {

    override fun getCandidates(): Flow<List<Candidate>> {
        return dao.getCandidates()
    }
    override suspend fun fetchCandidates() = mainApi.getCandidates()

}