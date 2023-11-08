package com.fetch.codingassignment.edisonzhangsolution.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.codingassignment.edisonzhangsolution.model.Candidate
import com.fetch.codingassignment.edisonzhangsolution.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _candidatesListState = mutableStateOf(CandidatesListState())
    val candidatesListState: State<CandidatesListState> = _candidatesListState

    data class CandidatesListState(
        val loading: Boolean = true,
        val list: List<Candidate> = emptyList(),
        val error: String? = null
    )

    init {
        fetchCandidates()
    }

    private fun fetchCandidates() {
        viewModelScope.launch {
            try {
                val response = mainRepository.getCandidates()
                _candidatesListState.value = _candidatesListState.value.copy(
                    list = response.filter{ candidate -> !candidate.name.isNullOrBlank()}.sortedWith(compareBy({it.listId.toInt()}, {it.name})),
                    loading = false,
                    error = null
                )
            }catch(e: Exception){
                _candidatesListState.value = _candidatesListState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

}