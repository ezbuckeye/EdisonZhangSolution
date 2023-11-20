package com.fetch.codingassignment.edisonzhangsolution.ui.candidate_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.codingassignment.edisonzhangsolution.model.Candidate
import com.fetch.codingassignment.edisonzhangsolution.model.MainRepositoryImpl
import com.fetch.codingassignment.edisonzhangsolution.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandidateDetailViewModel @Inject constructor(private val mainRepository: MainRepositoryImpl): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    suspend fun getCandidateDetail(id: Int): Candidate {
        return mainRepository.getCandidateDetail(id)
    }

    fun onEvent(event: CandidateDetailEvent) {
        when(event) {
            is CandidateDetailEvent.onClickBack -> {
                sendUiEvent(UiEvent.NavigateBackToCandidatesScreen)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}