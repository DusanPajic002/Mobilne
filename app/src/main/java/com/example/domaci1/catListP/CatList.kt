package com.example.domaci1.catListP


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.domaci1.breeds.CatListUI


@ExperimentalMaterial3Api
fun NavGraphBuilder.catListScreen(
    route: String,
    navController: NavController,
) = composable(route = route) {
    val catListViewModel = viewModel<CatListViewModel>()
    val state by catListViewModel.state.collectAsState()

    CatList(
        state = state,
        onItemClick = {
            navController.navigate(route = "cat/${it.id}")
        },
    )
}

@ExperimentalMaterial3Api
@Composable
fun CatList(
    state: CatListState,
    onItemClick: (CatListUI) -> Unit,
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "CatList") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            )
        },
        content = {

            CatsList(
                paddingValues = it,
                items = state.cats,
                onItemClick = onItemClick,
            )

            if (state.cats.isEmpty()) {
                when (state.fetching) {
                    true -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    false -> {
                        if (state.error != null) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                val errorMessage = when (state.error) {
                                    is CatListState.ListError.ListUpdateFailed ->
                                        "Failed to load. Error message: ${state.error.cause?.message}."
                                }
                                Text(text = errorMessage)
                            }
                        } else {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(text = "No cats.")
                            }
                        }
                    }
                }
            }

        }
    )
}

@ExperimentalMaterial3Api
@Composable
private fun CatsList(
    items: List<CatListUI>,
    paddingValues: PaddingValues,
    onItemClick: (CatListUI) -> Unit,
) {
    val scrollState = rememberScrollState()
    val textState = remember { mutableStateOf("") }
    val filteredItems = remember { mutableStateOf(items) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(items) {
        filteredItems.value = items
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Filter Cats") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 2.dp)
                .padding(top = 8.dp)
        )

        Button(
            onClick = {
                focusManager.clearFocus()
                filteredItems.value = items.filter {
                    it.name.startsWith(textState.value, ignoreCase = true)
                }

            },
            modifier = Modifier
                .width(150.dp)
                .padding(bottom = 14.dp),
        ) {
            Text("Filter")
        }

        filteredItems.value.forEach {
            Column {
                key(it.name) {
                    CatListItem(
                        data = it,
                        onClick = {
                            onItemClick(it)
                        },
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }
}

fun List<String>.pickRandom(n: Int): List<String> {
    if (this.size <= n) return this
    return this.shuffled().take(n)
}
@Composable
fun SuggestionChipExample(personalityTraits: List<String>) {
    val randomTraits = remember { personalityTraits.pickRandom(3) }
    randomTraits.forEach { trait ->
        SuggestionChip(
            modifier = Modifier.padding(end = 4.dp),
            onClick = {},
            label = { Text(trait) }
        )
    }
}

@Composable
private fun CatListItem(
    data: CatListUI,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
    ) {
        Text(            // cat name
            modifier = Modifier.padding(all = 16.dp),
            text = data.name,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp,
        )
        Row { // alternative names
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
                    .weight(weight = 1f),
                text = data.alt_names,
            )
        }
        Row { // description
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
                    .weight(weight = 1f),
                text = if (data.description.length > 100)
                    data.description.substring(0, 100) + "..."
                else data.description,
            )

            Icon(
                modifier = Modifier.padding(end = 16.dp),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
            )
        }   //personalityTraits
        Row(modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)) { // personality traits
            SuggestionChipExample(data.temperament)
        }
    }
}