package org.sopt.and.presentation.screens

sealed class Routes (val screen: String){
    object SignUp: Routes("signup")
    object Login: Routes("login")
    object Home: Routes("home")
    object Search: Routes("search")
    object My: Routes("my")
}