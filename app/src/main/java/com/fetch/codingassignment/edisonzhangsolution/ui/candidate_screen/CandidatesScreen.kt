package com.fetch.codingassignment.edisonzhangsolution.ui.candidate_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fetch.codingassignment.edisonzhangsolution.model.Candidate
import com.fetch.codingassignment.edisonzhangsolution.util.UiEvent
import com.fetch.codingassignment.edisonzhangsolution.util.UiState

@Composable
fun CandidatesScreen(viewModel: CandidatesViewModel = hiltViewModel<CandidatesViewModel>(), modifier: Modifier = Modifier) {
    val candidates = viewModel.candidates.collectAsState(initial = emptyList()).value
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = event.message,
                        withDismissAction = true
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) { it ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            CandidatesAppHeader(viewModel)
            when (uiState) {
                UiState.loading -> CircularProgressIndicator(modifier.align(Alignment.CenterHorizontally))
                UiState.success, UiState.error -> CandidatesTable(candidates, viewModel::onEvent)
            }
        }
    }
}



