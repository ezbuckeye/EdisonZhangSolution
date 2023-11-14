package com.fetch.codingassignment.edisonzhangsolution

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fetch.codingassignment.edisonzhangsolution.ui.theme.EdisonZhangSolutionTheme
import com.fetch.codingassignment.edisonzhangsolution.ui.candidate_screen.CandidatesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EdisonZhangSolutionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CandidatesScreen()
                }
            }
        }
    }
}