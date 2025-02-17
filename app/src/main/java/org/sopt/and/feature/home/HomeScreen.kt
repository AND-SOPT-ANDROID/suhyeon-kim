package org.sopt.and.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.Banner
import org.sopt.and.core.designsystem.component.EditorRecommendBox
import org.sopt.and.core.designsystem.component.TodayTop20Box
import org.sopt.and.core.designsystem.component.WavveTabBar
import org.sopt.and.ui.theme.WavveTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(
        pageCount = { 6 }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.iv_wavve_logo),
                        contentDescription = stringResource(R.string.wavve_logo),
                        modifier = Modifier
                            .size(90.dp)
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier.padding(end = 15.dp),
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = stringResource(R.string.wifi),
                        tint = Color.White
                    )
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = stringResource(R.string.live),
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
                WavveTabBar(viewModel.homeTabText)

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

                EditorRecommendTitle()
                LazyRow(
                    modifier = Modifier.padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp),
                ) {
                    items(items = viewModel.editorDummy, key = { it.ranking }) { item ->
                        EditorRecommendBox(topData = item, modifier = Modifier)
                    }
                }

                TodayTop20Title()
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(items = viewModel.top20Dummy, key = { it.ranking }) { item ->
                        TodayTop20Box(topData = item, modifier = Modifier.padding(bottom = 90.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun EditorRecommendTitle(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp),
    ) {
        Text(
            text = stringResource(R.string.editor_recommend_title),
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = stringResource(R.string.more),
            tint = Color.White,
        )
    }
}

@Composable
private fun TodayTop20Title(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(horizontal = 20.dp),
        text = stringResource(R.string.today_top_20_title),
        fontSize = 20.sp,
        color = Color.White
    )
}