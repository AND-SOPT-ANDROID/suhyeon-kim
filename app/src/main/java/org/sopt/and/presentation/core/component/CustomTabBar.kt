package org.sopt.and.presentation.core.component

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.WavveTheme


@Composable
fun WavveTabBar(
    tabTitles: List<String>, modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        containerColor = WavveTheme.colors.BackgroundGray,
        contentColor = Color.White,
        indicator = {},
        divider = {}
    ) {
        tabTitles.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = {
                    Text(
                        text = tab,
                        fontSize = 18.sp,
                        color = if (selectedTabIndex == index) Color.White else Color.Gray
                    )
                }
            )
        }
    }
}