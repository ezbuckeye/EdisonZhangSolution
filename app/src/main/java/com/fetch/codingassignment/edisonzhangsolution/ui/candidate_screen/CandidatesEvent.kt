package com.fetch.codingassignment.edisonzhangsolution.ui.candidate_screen

sealed class CandidatesEvent {
    object OnSyncClick: CandidatesEvent()
    object OnDropdownClick: CandidatesEvent()
    object OnDropdownDismiss: CandidatesEvent()
    data class OnListIdSelect(val listId: Int): CandidatesEvent()
}

