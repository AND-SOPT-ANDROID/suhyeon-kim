package org.sopt.and.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = WavveTheme.colors.BackgroundGray)
                .padding(innerPadding)
        ) {
            Text(
                "검색",
                color = Color.White
            )
        }
    }
}