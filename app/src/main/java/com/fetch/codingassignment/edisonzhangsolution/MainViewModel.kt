package com.fetch.codingassignment.edisonzhangsolution

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _hiringListState = mutableStateOf(HiringListState())
    val hiringListState: State<HiringListState> = _hiringListState

    data class HiringListState(
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
                val response = hiringService.getCandidates()
                _hiringListState.value = _hiringListState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            }catch(e: Exception){
                _hiringListState.value = _hiringListState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

}