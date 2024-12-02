package com.example.studysmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.studysmart.ui.presentation.StudySmartTheme
import com.example.studysmart.ui.presentation.dashboard.DashBoardScreen
import com.example.studysmart.ui.presentation.session.SessionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudySmartTheme {
               SessionScreen()

            }
        }
    }
}

