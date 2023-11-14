package com.fetch.codingassignment.edisonzhangsolution.util

sealed class UiEvent {
    data class ShowSnackbar(
        val message: String
    ): UiEvent()
}