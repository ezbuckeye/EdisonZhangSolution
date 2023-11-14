package com.fetch.codingassignment.edisonzhangsolution.ui.candidate_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fetch.codingassignment.edisonzhangsolution.model.Candidate
import com.fetch.codingassignment.edisonzhangsolution.model.MainRepository
import com.fetch.codingassignment.edisonzhangsolution.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CandidatesViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val candidates = mainRepository.getCandidates()

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var expanded by mutableStateOf<Boolean>(false)
        private set

    fun onEvent(event: CandidatesEvent) {
        when(event) {
            is CandidatesEvent.OnDropdownClick -> {
                expanded = true
            }
            is CandidatesEvent.OnDropdownDismiss -> {
                expanded = false
            }
            is CandidatesEvent.OnListIdSelect -> TODO()
            is CandidatesEvent.OnSyncClick -> TODO()
        }
    }

//    private val _candidatesListState = mutableStateOf(CandidatesListState())
//    val candidatesListState: State<CandidatesListState> = _candidatesListState
//
//    data class CandidatesListState(
//        val loading: Boolean = true,
//        val list: List<Candidate> = emptyList(),
//        val error: String? = null
//    )

    /*
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
     */

}