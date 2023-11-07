package com.fetch.codingassignment.edisonzhangsolution

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
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
                CandidateScreen(hiringListState.list)
            }
        }
    }
}

@Composable
fun CandidateScreen(candidates: List<Candidate>) {
    LazyVerticalGrid(GridCells.Fixed(1), modifier = Modifier.fillMaxSize()) {
        items(candidates) {
            candidate ->
            CandidateItem(candidate = candidate)
        }
    }
}

@Composable
fun CandidateItem(candidate: Candidate) {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()) {
        Text(text = candidate.id.toString(),
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top=4.dp))
        Text(text = candidate.listId.toString(),
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top=4.dp))
        Text(text = candidate.name ?: "null",
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top=4.dp))

    }
}