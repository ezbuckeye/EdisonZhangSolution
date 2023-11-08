package com.fetch.codingassignment.edisonzhangsolution.model

import com.fetch.codingassignment.edisonzhangsolution.ApiService

class MainRepository constructor(private val apiService: ApiService) {

    suspend fun getCandidates() = apiService!!.getCandidates()

}