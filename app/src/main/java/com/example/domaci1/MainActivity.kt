package com.example.domaci1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.domaci1.navigation.ScreenManager
import com.example.domaci1.repository.Repository
import com.example.domaci1.ui.theme.Domaci1Theme

class MainActivity : ComponentActivity() {

    private val repository = Repository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Domaci1Theme {
                ScreenManager()
            }
        }
    }
}