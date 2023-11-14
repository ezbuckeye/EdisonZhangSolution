package com.fetch.codingassignment.edisonzhangsolution.ui.candidate_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fetch.codingassignment.edisonzhangsolution.R
import com.fetch.codingassignment.edisonzhangsolution.model.Candidate
import com.fetch.codingassignment.edisonzhangsolution.util.UiEvent
import com.fetch.codingassignment.edisonzhangsolution.util.UiState

@Composable
fun CandidatesScreen(viewModel: CandidatesViewModel = hiltViewModel<CandidatesViewModel>(), modifier: Modifier = Modifier) {
//    val candidatesListState by viewModel.candidatesListState
    val candidates = viewModel.candidates.collectAsState(initial = emptyList())
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
        Column(modifier = Modifier.fillMaxSize().padding(it)) {
            AppHeader(viewModel)
            when (uiState) {
                UiState.loading -> CircularProgressIndicator(modifier.align(Alignment.CenterHorizontally))
                UiState.success, UiState.error -> CandidateTable(candidates.value)
            }
        }
    }
}

@Composable
fun AppHeader(viewModel: CandidatesViewModel){
    Row(modifier = Modifier
        .fillMaxHeight(0.15f)
        .fillMaxWidth()
        .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Image(painter = painterResource(id = R.drawable.fetch_rewards_logo), contentDescription = "fetch app logo")
        ButtonGroup(viewModel)
    }
}

@Composable
fun ButtonGroup(viewModel: CandidatesViewModel) {
    Row(modifier = Modifier
        .fillMaxHeight()
        .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(onClick = { viewModel.onEvent(CandidatesEvent.OnSyncClick) }) {
            Text(text = "sync")
        }
        Spacer(modifier = Modifier.width(8.dp))
        DropDownFilter(viewModel)
    }
}

@Composable
fun DropDownFilter(viewModel: CandidatesViewModel) {
    val expanded = viewModel.expanded
    Box {
        Button(onClick = { viewModel.onEvent(CandidatesEvent.OnDropdownClick) }) {
            Text(text = "filter")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { viewModel.onEvent(CandidatesEvent.OnDropdownDismiss) }
        ) {
            DropdownMenuItem(
                text = {Text("1")},
                onClick = {}
            )
            DropdownMenuItem(
                text = {Text("2")},
                onClick = {}
            )
        }
    }
}

@Composable
fun CandidateTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .border(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = "ID",
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "List ID",
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Name",
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun CandidateTableRow(candidate: Candidate) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = candidate.id.toString(),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        Text(
            text = candidate.listId.toString(),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        Text(
            text = candidate.name.orEmpty(),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
    }
}


@Composable
fun CandidateTable(candidates: List<Candidate>) {
    Column {
        CandidateTableHeader()
        LazyColumn {
            items(candidates) { candidate ->
                CandidateTableRow(candidate = candidate)
            }
        }
    }
}