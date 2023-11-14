package com.fetch.codingassignment.edisonzhangsolution.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Candidate(@PrimaryKey val id: Int,
                     val listId: Int,
                     val name: String? = null)

