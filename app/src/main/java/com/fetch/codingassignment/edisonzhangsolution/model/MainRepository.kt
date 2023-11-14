package com.fetch.codingassignment.edisonzhangsolution.model

class MainRepository constructor(private val mainApi: MainApi) {

    suspend fun getCandidates() = mainApi!!.getCandidates()

}