package com.example.domaci1.model

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domaci1.repository.DataFile
import com.example.domaci1.repository.Repository
import com.example.domaci1.ui.theme.Domaci1Theme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun ScreenManager() {

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(
            navController = navController,
            startDestination = "cats",
        ) {
            composable(
                route = "cats",
            ) {
                CatList(
                    items = Repository().allCats(),
                    onItemClick = {
                        navController.navigate(route = "cat/${it.id}")
                    },
                )
            }

            composable(
                route = "cat/{id}",
            ) {navBackStackEntry ->
                    val loginDataId = navBackStackEntry.getCatIdOrThrow()
                    val data = remember(loginDataId) {
                        Repository().getById(id = loginDataId)
                    }
                    if (data != null) {
                        CatProfile(
                            cat = data,
                            onClose = {
                                navController.navigateUp()
                            }
                        )
                    } else
                        CatNotFound()
                }
            }

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