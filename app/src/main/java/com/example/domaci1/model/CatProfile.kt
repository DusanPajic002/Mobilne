package com.example.domaci1.model

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domaci1.repository.DataFile
import com.example.domaci1.ui.theme.Domaci1Theme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun CatProfile(
    cat: Cat,
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
        content = {
            val scrollState = rememberScrollState()
            Card(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(it)
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
                    text = "Origin:  " + cat.origin,
                    fontSize = 18.sp,
                )
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = "Size:  " + cat.size,
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
                    text = "Personality:  " + cat.personalityTraits.joinToString(", "),
                    fontSize = 18.sp,
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
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(cat.wikiUrl))
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
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewCatProfileScreen() {
    Domaci1Theme {
        CatProfile(
            cat = DataFile.toMutableList()[0],
            onClose = {}
        )
    }
}