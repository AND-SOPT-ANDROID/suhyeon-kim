package org.sopt.and.screens.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.component.EmptyBox
import org.sopt.and.component.ProfileBox
import org.sopt.and.component.TicketBox
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.viewmodel.MyViewModel

@Composable
fun MyScreen(navController: NavController, viewModel: MyViewModel = viewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }

    //화면 진입 시 snackBar 표시
    LaunchedEffect(Unit) {
        snackbarHostState.showSnackbar("환영합니다!")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = WavveTheme.colors.BackgroundGray)
                .padding(innerPadding)
        ) {
            Column {
                //프로필 박스
                ProfileBox(viewModel)

                Spacer(modifier = Modifier.height(1.dp))

                //이용권 박스
                TicketBox()

                Spacer(modifier = Modifier.height(20.dp))

                //전체 시청 내역
                EmptyBox(
                    stringResource(R.string.ViewingHistory),
                    stringResource(R.string.NoViewingHistory),
                )

                Spacer(modifier = Modifier.height(30.dp))

                //관심 프로그램
                EmptyBox(
                    stringResource(R.string.InterestedProgram),
                    stringResource(R.string.NoInterestedProgram),
                )
            }
        }
    }
}