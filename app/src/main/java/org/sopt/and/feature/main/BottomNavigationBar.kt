package org.sopt.and.feature.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.utils.currentRoute

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    BottomAppBar(
        modifier = Modifier.height(110.dp),
        containerColor = Color.Black,
    ) {
        for (item in NavItem.entries) {
            NavigationBarItem(
                icon = item.icon,
                label = { Text(stringResource(item.labelResource)) },
                selected = navController.currentRoute() == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(0)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Black
                )
            )
        }
    }
}


enum class NavItem(
    val icon: @Composable () -> Unit,
    val labelResource: Int,
    val route: String,
) {
    Home(
        icon = { Icon(Icons.Outlined.Home, contentDescription = stringResource(R.string.home)) },
        labelResource = R.string.home,
        route = Routes.Home.screen
    ),
    Search(
        icon = {
            Icon(
                Icons.Outlined.Search,
                contentDescription = stringResource(R.string.search)
            )
        },
        labelResource = R.string.search,
        route = Routes.Search.screen
    ),
    My(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.my),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(30.dp)
            )
        },
        labelResource = R.string.my,
        route = Routes.My.screen
    )
}