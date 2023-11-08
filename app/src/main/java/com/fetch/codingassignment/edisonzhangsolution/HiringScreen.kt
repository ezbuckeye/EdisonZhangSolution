package com.fetch.codingassignment.edisonzhangsolution

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HiringScreen(modifier: Modifier = Modifier) {
    val hiringViewModel: MainViewModel = viewModel()
    val hiringListState by hiringViewModel.hiringListState
    Box(modifier = Modifier.fillMaxSize()) {
        when{
            hiringListState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            hiringListState.error!=null -> {
                Text(hiringListState.error ?: "ERROR OCCURED")
            }
            else -> {
                // Display Candidates
                CandidateTable(hiringListState.list)
            }
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
            modifier = Modifier.weight(1f).padding(8.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "List ID",
            modifier = Modifier.weight(1f).padding(8.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Name",
            modifier = Modifier.weight(1f).padding(8.dp),
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
            modifier = Modifier.weight(1f).padding(8.dp)
        )
        Text(
            text = candidate.listId.toString(),
            modifier = Modifier.weight(1f).padding(8.dp)
        )
        Text(
            text = candidate.name.orEmpty(),
            modifier = Modifier.weight(1f).padding(8.dp)
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