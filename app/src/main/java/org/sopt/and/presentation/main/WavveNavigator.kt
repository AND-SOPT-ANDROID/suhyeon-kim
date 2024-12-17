package org.sopt.and.presentation.main

import androidx.navigation.NavController

fun NavController.navigateToSignUp() {
    navigate(Routes.SignUp.screen)
}

fun NavController.navigateToLogin() {
    navigate(Routes.Login.screen)
}