package org.sopt.and.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavController.currentRoute(): String? {
    val navBackStackEntry by currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}