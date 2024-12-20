package org.sopt.and.presentation.mypage

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.R
import org.sopt.and.presentation.core.component.EmptyBox
import org.sopt.and.presentation.core.component.ProfileBox
import org.sopt.and.presentation.core.component.TicketBox
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun MyRoute(
    viewModel: MyViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("loginToken", "").orEmpty()
    val myState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setEvent(MyContract.MyEvent.GetUserHobby(token = token))
    }

    MyScreen(state = myState)
}

@Composable
fun MyScreen(
    state: MyContract.MyUiState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = WavveTheme.colors.BackgroundGray)
                .padding(innerPadding)
        ) {
            Column {
                ProfileBox(
                    userEmail = state.hobby,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(1.dp))

                TicketBox()

                Spacer(modifier = Modifier.height(20.dp))

                EmptyBox(
                    stringResource(R.string.viewing_history),
                    stringResource(R.string.no_viewing_history),
                )

                Spacer(modifier = Modifier.height(30.dp))

                EmptyBox(
                    stringResource(R.string.interested_program),
                    stringResource(R.string.no_interested_program),
                )
            }
        }
    }
}