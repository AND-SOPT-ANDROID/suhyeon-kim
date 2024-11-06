package org.sopt.and.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.and.feature.home.HomeScreen
import org.sopt.and.feature.home.HomeViewModel
import org.sopt.and.feature.login.LoginScreen
import org.sopt.and.feature.login.LoginViewModel
import org.sopt.and.feature.mypage.MyScreen
import org.sopt.and.feature.mypage.MyViewModel
import org.sopt.and.feature.search.SearchScreen
import org.sopt.and.feature.search.SearchViewModel
import org.sopt.and.feature.signup.SignUpScreen
import org.sopt.and.feature.signup.SignUpViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    isBottomAppBarVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    var isBottomAppBarVisible1 = isBottomAppBarVisible
    NavHost(
        navController = navController,
        startDestination = Routes.Login.screen,
        modifier = Modifier
    ) {
        composable(Routes.Login.screen) {
            isBottomAppBarVisible1 = false
            LoginScreen(navController = navController, viewModel = LoginViewModel())
        }
        composable(Routes.SignUp.screen) {
            isBottomAppBarVisible1 = false
            SignUpScreen(
                navController = navController,
                viewModel = SignUpViewModel()
            )
        }
        composable(Routes.Home.screen) {
            isBottomAppBarVisible1 = true
            HomeScreen(navController = navController, viewModel = HomeViewModel())
        }
        composable(Routes.Search.screen) {
            isBottomAppBarVisible1 = true
            SearchScreen(navController = navController, viewModel = SearchViewModel())
        }
        composable(Routes.My.screen) {
            isBottomAppBarVisible1 = true
            MyScreen(navController = navController, viewModel = MyViewModel())
        }
    }
}