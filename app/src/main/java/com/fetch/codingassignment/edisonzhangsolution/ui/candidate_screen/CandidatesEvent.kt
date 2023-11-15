package com.fetch.codingassignment.edisonzhangsolution.ui.candidate_screen

import com.fetch.codingassignment.edisonzhangsolution.model.Candidate

sealed class CandidatesEvent {
    object OnSyncClick: CandidatesEvent()
    object OnDropdownClick: CandidatesEvent()
    object OnDropdownDismiss: CandidatesEvent()
    data class OnListIdSelect(val listId: Int): CandidatesEvent()

    data class OnClickCandidate(val candidate: Candidate): CandidatesEvent()
}

