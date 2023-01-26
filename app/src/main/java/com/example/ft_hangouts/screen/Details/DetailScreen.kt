package com.example.ft_hangouts.screen.Details

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ft_hangouts.Routes
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
    Log.d(TAG, "do we retrieve person info $person")
    Log.d(TAG, "Details = ${details.partnerInfo}")

    Spacer(modifier = Modifier.height(20.dp))
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("EditPersonInfo/$person") },
            ) {
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth(1f)) {
            // Add a single item
            item {
                Text(text = "My firstname = ${details.partnerInfo.firstName}")
            }
            item {
                Text(text = "My lastname = ${details.partnerInfo.lastName}")
            }
            item {
                Text(text = "My profession = ${details.partnerInfo.profession}")
            }
            item {
                Text(text = "My favorite beer = ${details.partnerInfo.epicBeer}")
            }
            item {
                Text(text = "My favorite animal = ${details.partnerInfo.favoriteAnimal}")
            }
        }
    }

}