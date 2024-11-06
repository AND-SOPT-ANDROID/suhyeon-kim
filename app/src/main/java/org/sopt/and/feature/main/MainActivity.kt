package org.sopt.and.feature.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val navController = rememberNavController()
                val isBottomAppBarVisible by remember { mutableStateOf(true) }
                val selected = remember {
                    mutableStateOf("홈")
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (isBottomAppBarVisible) {
                            BottomNavigationBar(selected, navController)
                        }
                    }
                ) {
                    NavGraph(navController, isBottomAppBarVisible)
                }
            }
        }
    }
}
