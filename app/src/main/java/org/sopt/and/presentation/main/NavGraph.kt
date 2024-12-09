package org.sopt.and.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.and.R
import org.sopt.and.presentation.home.HomeScreen
import org.sopt.and.presentation.login.LoginScreen
import org.sopt.and.presentation.mypage.MyScreen
import org.sopt.and.presentation.search.SearchScreen
import org.sopt.and.presentation.signup.SignUpScreen
import org.sopt.and.utils.toast

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var isLoggedIn by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isLoggedIn) {
        if (isLoggedIn) {
            context.toast(context.getString(R.string.welcome))
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Login.screen,
        modifier = Modifier
    ) {


        composable(Routes.Login.screen) {
            isLoggedIn = false
            LoginScreen(
                navController = navController,
                onLoginSuccess = { name, password ->
                    navController.navigate(Routes.Home.screen) {
                        popUpTo(Routes.Home.screen) { inclusive = true }
                    }
                },
            )
        }
        composable(Routes.SignUp.screen) {
            SignUpScreen(
                navController = navController,
                onSignUpSuccess = { name, password ->
                    navController.navigate(Routes.Login.screen) {
                        popUpTo(Routes.Login.screen) { inclusive = true }
                    }
                },
            )
        }
        composable(Routes.Home.screen) {
            isLoggedIn = true
            HomeScreen(navController = navController)
        }
        composable(Routes.Search.screen) {
            SearchScreen(navController = navController)
        }
        composable(Routes.My.screen) {
            MyScreen(
                navController = navController,
            )
        }
    }
}