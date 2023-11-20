package com.fetch.codingassignment.edisonzhangsolution

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fetch.codingassignment.edisonzhangsolution.ui.candidate_detail_screen.CandidateDetailScreen
import com.fetch.codingassignment.edisonzhangsolution.ui.theme.EdisonZhangSolutionTheme
import com.fetch.codingassignment.edisonzhangsolution.ui.candidates_screen.CandidatesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EdisonZhangSolutionTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "candidates_screen") {
                    composable("candidates_screen") {
                        CandidatesScreen(navController = navController)
                    }
                    composable("candidate_detail_screen/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                            }
                        )
                        ) {
                        val candidateId = it.arguments!!.getInt("id");
                        CandidateDetailScreen(id = candidateId);
                    }
                }

            }
        }
    }
}