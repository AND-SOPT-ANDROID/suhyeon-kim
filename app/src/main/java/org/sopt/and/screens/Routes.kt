package org.sopt.and.screens

sealed class Routes (val screen: String){
    object SignUp: Routes("signup")
    object Login: Routes("login")
    object My: Routes("my")
}