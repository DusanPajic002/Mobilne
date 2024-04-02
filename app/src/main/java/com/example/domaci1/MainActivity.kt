package com.example.domaci1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.domaci1.model.CatList
import com.example.domaci1.model.ScreenManager
import com.example.domaci1.repository.DataFile
import com.example.domaci1.repository.Repository
import com.example.domaci1.ui.theme.Domaci1Theme

class MainActivity : ComponentActivity() {

    private val repository = Repository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Domaci1Theme {
                ScreenManager(
                    cats = repository.allCats(),
                    onUpdate = { },
                    onDelete = { })
                }
           /*     CatList(
                    items = repository.allCats(),
                    onItemClick = { }
                )*/
            }
        }
}