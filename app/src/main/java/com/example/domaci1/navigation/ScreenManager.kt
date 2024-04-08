package com.example.domaci1.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.domaci1.catListP.catListScreen
import com.example.domaci1.catProfile.catProfileScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenManager() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "cats",
    ) {
        catListScreen(
            route = "cats",
            navController = navController,
        )
        catProfileScreen(
            route = "cat/{id}",
            navController = navController,
        )

    }

}
