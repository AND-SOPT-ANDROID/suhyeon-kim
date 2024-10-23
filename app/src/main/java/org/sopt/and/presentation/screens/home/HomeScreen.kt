package org.sopt.and.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.viewmodel.MyViewModel
import org.sopt.and.presentation.component.Banner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: MyViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(
        pageCount = { 6 }
    )

    val homeTabText = listOf(
        "뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.iv_wavve_logo),
                        contentDescription = "stringResource(R.string.wavve_logo)",
                        modifier = Modifier
                            .size(90.dp)
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier.padding(end = 15.dp),
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = "WIFI",
                        tint = Color.White
                    )
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "LIVE",
                        tint = Color.White
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = WavveTheme.colors.BackgroundGray)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = WavveTheme.colors.BackgroundGray)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                TabTitle(homeTabText)

                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 20.dp),
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    pageSpacing = 10.dp
                ) {
                    Banner(pagerState)
                }

            }
        }
    }
}

@Composable
private fun TabTitle(homeTabText: List<String>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        homeTabText.forEach { tab ->
            Text(
                text = tab,
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    }
}