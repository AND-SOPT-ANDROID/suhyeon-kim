package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.screens.Routes
import org.sopt.and.screens.login.LoginScreen
import org.sopt.and.screens.mypage.MyScreen
import org.sopt.and.screens.signup.SignUpScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "signup",
                ) {
                    composable(Routes.Login.screen) {
                        LoginScreen(navController = navController)
                    }
                    composable(Routes.SignUp.screen) {
                        SignUpScreen(navController = navController)
                    }
                    composable(Routes.My.screen) {
                        MyScreen(navController = navController)
                    }
                }
            }
        }
    }
}
