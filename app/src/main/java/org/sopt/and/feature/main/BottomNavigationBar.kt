package org.sopt.and.feature.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.utils.noRippleClickable

@Composable
fun BottomNavigationBar(
    selected: MutableState<String>,
    navController: NavHostController
) {
    BottomAppBar(
        modifier = Modifier.height(110.dp),
        containerColor = Color.Black,
    ) {
        NavItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = stringResource(R.string.home),
                    modifier = Modifier.padding(bottom = 5.dp),
                    tint = if (selected.value == "홈") Color.White else Color.Gray
                )
            },
            label = stringResource(R.string.home),
            selected = selected,
            modifier = Modifier
                .noRippleClickable {
                    selected.value = "홈"
                    navController.navigate(Routes.Home.screen) {
                        popUpTo(0)
                    }
                }
        )
        NavItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.search),
                    modifier = Modifier.padding(bottom = 5.dp),
                    tint = if (selected.value == "검색") Color.White else Color.Gray
                )
            },
            label = stringResource(R.string.search),
            selected = selected,
            modifier = Modifier
                .noRippleClickable {
                    selected.value = "검색"
                    navController.navigate(Routes.Search.screen) {
                        popUpTo(0)
                    }
                }
        )
        NavItem(
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = stringResource(R.string.my),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(30.dp)
                )
            },
            label = stringResource(R.string.my),
            selected = selected,
            modifier = Modifier
                .noRippleClickable {
                    selected.value = "MY"
                    navController.navigate(Routes.My.screen) {
                        popUpTo(0)
                    }
                }
        )
    }
}

@Composable
fun RowScope.NavItem(
    icon: @Composable () -> Unit,
    label: String,
    selected: MutableState<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon()
        Text(
            text = label,
            color = if (selected.value == label) Color.White else Color.Gray
        )
    }
}