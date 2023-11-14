package com.fetch.codingassignment.edisonzhangsolution.ui.candidate_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fetch.codingassignment.edisonzhangsolution.model.Candidate

@Composable
fun CandidatesTableHeader() {
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
fun CandidatesTableRow(candidate: Candidate) {
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
fun CandidatesTable(candidates: List<Candidate>) {
    Column {
        CandidatesTableHeader()
        LazyColumn {
            items(candidates) { candidate ->
                CandidatesTableRow(candidate = candidate)
            }
        }
    }
}