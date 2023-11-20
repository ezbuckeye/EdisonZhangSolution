package com.fetch.codingassignment.edisonzhangsolution.model

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val mainApi: MainApi, private val dao: CandidateDao) : MainRepository {

    override fun getCandidates(): Flow<List<Candidate>> {
        return dao.getCandidates()
    }

    override fun getListIds(): Flow<List<Int>> {
        return dao.getListIds()
    }

    override suspend fun fetchCandidates(): Response<List<Candidate>> {
        val response: Response<List<Candidate>> = mainApi.getCandidates()
        if(response.isSuccessful) {
            // remove the previous data from db
            dao.dropCandidates()
            // add the updated data to db
            dao.addCandidates(response.body()!!)
        }
        return response
    }

    override suspend fun getCandidateDetail(id: Int): Candidate {
        return dao.getCandidate(id)
    }

}