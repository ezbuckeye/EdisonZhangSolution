package com.fetch.codingassignment.edisonzhangsolution.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fetch.codingassignment.edisonzhangsolution.ApiService
import com.fetch.codingassignment.edisonzhangsolution.model.MainRepository
import com.fetch.codingassignment.edisonzhangsolution.ui.theme.EdisonZhangSolutionTheme
import com.fetch.codingassignment.edisonzhangsolution.viewmodel.MainViewModel
import com.fetch.codingassignment.edisonzhangsolution.viewmodel.MyViewModelFactory

class MainActivity : ComponentActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = ApiService.getInstance()
        val mainRepository = MainRepository(apiService = apiService)
        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java);
        setContent {
            EdisonZhangSolutionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CandidatesScreen(viewModel)
                }
            }
        }
    }
}