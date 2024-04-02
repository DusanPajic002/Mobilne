package com.example.domaci1.model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domaci1.repository.DataFile
import com.example.domaci1.ui.theme.Domaci1Theme

@ExperimentalMaterial3Api
@Composable
fun CatList(
    items: List<Cat>,
    onItemClick: (Cat) -> Unit
) {
    val textState = remember { mutableStateOf("") }
    val filteredItems = remember { mutableStateOf(items) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            Column ( )
            {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Cats") },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color(0xFFf5d742) // Ovo je svetlo plava boja.
                    )
                )
                Divider()

            }
        },
        content = {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .clickable {focusManager.clearFocus()}
                    .padding(it),
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
                        .padding(top = 16.dp)
                        .padding(bottom = 2.dp)
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
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 14.dp),
                ) {
                    Text("Filter")
                }

                Spacer(modifier = Modifier.height(16.dp))
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
    )
}

@Composable
private fun CatListItem(
    data: Cat,
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
                text = data.alternativeNames.joinToString(", "),
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
            SuggestionChipExample(data.personalityTraits)
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


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewCatListScreen() {
    Domaci1Theme {
        CatList(
            items = DataFile,
            onItemClick = {},
        )
    }
}