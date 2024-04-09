package com.example.domaci1.catProfile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.domaci1.Acore.compose.NoDataContent
import com.example.domaci1.breeds.CatProfileUI


@ExperimentalMaterial3Api
fun NavGraphBuilder.catProfileScreen(
    route: String,
    navController: NavController,
) = composable(
    route = route,
) { navBackStackEntry ->
    val dataId = navBackStackEntry.arguments?.getString("id")
        ?: throw IllegalArgumentException("id is required.")

    val catProfileViewModel = viewModel<CatProfileViewModel>(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CatProfileViewModel(catId = dataId) as T
            }
        },
    )
    val state = catProfileViewModel.state.collectAsState()

    CatProfile(
        state = state.value,
        onClose = {
            navController.navigateUp()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatProfile(
    state: CatProfileState,
    onClose: () -> Unit
){
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Profile") },
                    navigationIcon = {
                        IconButton(onClick = { onClose() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color(0xFFf5d742) // Ovo je svetlo plava boja.
                    )
                )
                Divider()
            }
        },
        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (state.fetching) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (state.error != null) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        val errorMessage = when (state.error) {
                            is CatProfileState.DetailsError.DataUpdateFailed ->
                                "Failed to load. Error message: ${state.error.cause?.message}."
                        }
                        Text(text = errorMessage)
                    }
                } else if (state.cat != null) {
                    CatData(
                        cat = state.cat,
                    )
                } else {
                    NoDataContent(id = state.catId)
                }
            }

        }
    )
}

@Composable
private fun CatData(
    cat: CatProfileUI,
) {
    val scrollState = rememberScrollState()
    Card(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            text = cat.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Rarity:  " + cat.isRare,
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Origin:  " + cat.originCountries.joinToString(", "),
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Size:  " + cat.averageWeight,
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Life span:  " + cat.lifeSpan,
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Personality:  " + cat.temperamentTraits.joinToString(", "),
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Adaptability: ${cat.adaptability}\n" +
                    "Affection Level: ${cat.affectionLevel}\n" +
                    "Child Friendly: ${cat.childFriendly}\n" +
                    "Dog Friendly: ${cat.dogFriendly}\n" +
                    "Energy Level: ${cat.energyLevel}",
            fontSize = 18.sp
        )
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Description:\n" + cat.description,
            fontSize = 18.sp,
        )

        val context = LocalContext.current
        Button(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .size(120.dp, 40.dp),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(cat.wikipedia_url))
                context.startActivity(intent)
            },
            contentPadding = PaddingValues(all = 8.dp),
        ) {
            Text(
                text = "Wiki",
                fontSize = 16.sp,
            )
        }
    }
}
