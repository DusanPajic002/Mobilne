package com.example.domaci1.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.domaci1.catListP.catListScreen
import com.example.domaci1.ui.theme.Domaci1Theme


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

        }

}

private fun NavBackStackEntry.getCatIdOrThrow(): String {
    return arguments?.getString("id") ?: throw IllegalStateException("id is required")
}

@Preview
@Composable
fun PreviewPasswordManagerApp() {
    Domaci1Theme {
        ScreenManager()
    }
}