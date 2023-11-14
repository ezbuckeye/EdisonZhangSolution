package com.fetch.codingassignment.edisonzhangsolution.ui.candidate_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fetch.codingassignment.edisonzhangsolution.R

@Composable
fun CandidatesAppHeader(viewModel: CandidatesViewModel){
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
    val listIds = viewModel.listIds.collectAsState(initial = emptyList()).value
    Box {
        Button(onClick = { viewModel.onEvent(CandidatesEvent.OnDropdownClick) }) {
            Text(text = "filter")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { viewModel.onEvent(CandidatesEvent.OnDropdownDismiss) }
        ) {
            listIds.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.toString() ) },
                    onClick = { viewModel.onEvent(CandidatesEvent.OnListIdSelect(it)) }
                )
            }
        }
    }
}