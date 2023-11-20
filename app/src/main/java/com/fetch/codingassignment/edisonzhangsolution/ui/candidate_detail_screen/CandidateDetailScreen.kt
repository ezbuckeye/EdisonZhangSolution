package com.fetch.codingassignment.edisonzhangsolution.ui.candidate_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fetch.codingassignment.edisonzhangsolution.model.Candidate
import com.fetch.codingassignment.edisonzhangsolution.util.UiEvent

@Composable
fun CandidateDetailScreen(id: Int, viewModel: CandidateDetailViewModel = hiltViewModel<CandidateDetailViewModel>(), navController: NavController) {
    val candidateDetail = produceState<Candidate?>(initialValue=null){
        value = viewModel.getCandidateDetail(id)
    }
    val avatarSize = 200.dp
    val topPadding = 20.dp
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.NavigateBackToCandidatesScreen -> {
                    navController.popBackStack()
                }
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)) {
        CandidateDetailTopSection(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter),
            onEvent = viewModel::onEvent
        )
        CandidateDetailSection(
            candidateDetail = candidateDetail.value,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = avatarSize/2f + topPadding,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .clip(RoundedCornerShape(10.dp))
                .shadow(2.dp, RoundedCornerShape(10.dp))
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        )

    }
}

@Composable
fun CandidateDetailTopSection(navController: NavController, modifier: Modifier = Modifier, onEvent: (CandidateDetailEvent)->Unit) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier
    ){
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(36.dp)
                .offset(16.dp, 16.dp)
                .clickable {
                    onEvent(CandidateDetailEvent.onClickBack)
                }
        )
    }
}

@Composable
fun CandidateDetailSection(candidateDetail: Candidate?, modifier: Modifier = Modifier) {
    if(candidateDetail==null) {
        CircularProgressIndicator()
    }else{
        Column(
            modifier=modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ){
            Text(text="Candidate ID: ${candidateDetail.id}")
            Text(text="Candidate Name: ${candidateDetail.name}")
            Text(text="Candidate ListID: ${candidateDetail.listId}")
        }
    }
}