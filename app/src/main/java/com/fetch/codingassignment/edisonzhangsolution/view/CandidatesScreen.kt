package com.fetch.codingassignment.edisonzhangsolution.view

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fetch.codingassignment.edisonzhangsolution.R
import com.fetch.codingassignment.edisonzhangsolution.model.Candidate
import com.fetch.codingassignment.edisonzhangsolution.viewmodel.MainViewModel

@Composable
fun CandidatesScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val candidatesListState by viewModel.candidatesListState
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader()
        Box(modifier = Modifier.fillMaxSize()) {
            when{
                candidatesListState.loading -> {
                    CircularProgressIndicator(modifier.align(Alignment.Center))
                }

                candidatesListState.error!=null -> {
                    Text(candidatesListState.error ?: "ERROR OCCURED")
                }
                else -> {
                    // Display Candidates
                    CandidateTable(candidatesListState.list)
                }
            }
        }
    }

}

@Composable
fun AppHeader(){
    Row(modifier = Modifier
        .fillMaxHeight(0.15f)
        .fillMaxWidth()
        .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Image(painter = painterResource(id = R.drawable.fetch_rewards_logo), contentDescription = "fetch app logo")
        ButtonGroup()
    }
}

@Composable
fun ButtonGroup() {
    Row(modifier = Modifier
        .fillMaxHeight()
        .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "sync")
        }
        Spacer(modifier = Modifier.width(8.dp))
        DropDownFilter()
    }
}

@Composable
fun DropDownFilter() {
    Box {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "filter")
        }
        DropdownMenu(
            expanded = false,
            onDismissRequest = {  }
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