package com.fetch.codingassignment.edisonzhangsolution.ui.candidate_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.codingassignment.edisonzhangsolution.model.Candidate
import com.fetch.codingassignment.edisonzhangsolution.model.MainRepository
import com.fetch.codingassignment.edisonzhangsolution.util.UiEvent
import com.fetch.codingassignment.edisonzhangsolution.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandidatesViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val candidates = mainRepository.getCandidates()

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var expanded by mutableStateOf<Boolean>(false)
        private set

    var uiState by mutableStateOf<UiState>(UiState.success)
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
            is CandidatesEvent.OnSyncClick -> {
                viewModelScope.launch {
                    uiState = UiState.loading
                    val response = mainRepository.fetchCandidates()
                    uiState = if(response.isSuccessful) UiState.success else UiState.error
                    if(uiState == UiState.error)
                        sendUiEvent(UiEvent.ShowSnackbar(message = "Synchronization failed due to ${response.message()}"))
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}