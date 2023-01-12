package com.example.ft_hangouts.screen.Details

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ft_hangouts.ViewModels.DetailsScreenViewModel

@Composable
fun DetailsScreen(
    navController: NavHostController,
    person: String?,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val details by viewModel.details.collectAsState()

    if (!viewModel.loaded) {
        if (person != null) {
            viewModel.getDetails(person)
        }
    }
    val TAG = "DetailsScreen"
    Text(text = "Personal details")
    Log.d(TAG, "do we retrieve person info $person")
    Log.d(TAG, "Details = ${details.partnerInfo}")
}