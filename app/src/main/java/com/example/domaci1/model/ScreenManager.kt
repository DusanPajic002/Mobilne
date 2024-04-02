package com.example.domaci1.model

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.domaci1.repository.DataFile
import com.example.domaci1.ui.theme.Domaci1Theme

sealed class AppScreen {
    data object CatList : AppScreen()
    data object CatProfile : AppScreen()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenManager(
    cats: List<Cat>,
    onUpdate: (Cat) -> Unit,
    onDelete: (Cat) -> Unit,
) {
       var activeScreen: AppScreen by remember { mutableStateOf(AppScreen.CatList) }

    Scaffold(
        content = { paddingValues ->
            AnimatedContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                targetState = activeScreen,
                label = "MainContent",
            ) { currentScreen ->
                when (currentScreen) {
                    is AppScreen.CatProfile -> {
                        CatProfile(
                            items = DataFile.get(0)
                        )
                    }
                    AppScreen.CatList -> {
                        CatList(
                            items = DataFile,
                            onItemClick = {
                                //activeScreen = AppScreen.PasswordEditor(it)
                            }
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewPasswordManagerApp() {
    Domaci1Theme {
        ScreenManager(
            cats = DataFile,
            onUpdate = { },
            onDelete = { }
        )
    }
}