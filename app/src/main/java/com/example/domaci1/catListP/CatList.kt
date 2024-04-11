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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
        eventPublisher = {
            catListViewModel.setEvent(it)
        },
        onItemClick = {
            navController.navigate(route = "cat/${it.id}")
        },
    )
}

@ExperimentalMaterial3Api
@Composable
fun CatList(
    state: CatListState,
    eventPublisher: (CatListState.FilterEvent) -> Unit,
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
                items = state.filteredCats,
                textfilt = state.filter,
                eventPublisher = eventPublisher,
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
    eventPublisher: (CatListState.FilterEvent) -> Unit,
    textfilt: String,
    onItemClick: (CatListUI) -> Unit,
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(paddingValues)
            .clickable { focusManager.clearFocus() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        TextField(
            value = textfilt,
            onValueChange = {
                eventPublisher(CatListState.FilterEvent.filterEvent(it))
            },
            label = { Text("Filter Cats") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 2.dp)
                .padding(top = 8.dp),
            trailingIcon = {
                if (textfilt.isNotEmpty()) {
                    IconButton(onClick = {
                        eventPublisher(CatListState.FilterEvent.filterEvent(""))
                        eventPublisher(CatListState.FilterEvent.filterClick)
                    }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear text")
                    }
                }
            }
        )

        Button(
            onClick = {
                focusManager.clearFocus()
                eventPublisher(CatListState.FilterEvent.filterClick)

            },
            modifier = Modifier
                .width(150.dp)
                .padding(bottom = 14.dp),
        ) {
            Text("Filter")
        }

        items.forEach {
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