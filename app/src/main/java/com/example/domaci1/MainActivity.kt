package com.example.domaci1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.domaci1.navigation.ScreenManager
import com.example.domaci1.Acore.theme.Domaci1Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Domaci1Theme {

                ScreenManager()
            }
        }
    }
}