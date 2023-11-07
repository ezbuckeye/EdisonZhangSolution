package com.fetch.codingassignment.edisonzhangsolution

data class Candidate(val id: Number,
                     val listId: Number,
                     val name: String)

data class Candidates(val candidates: List<Candidates>)