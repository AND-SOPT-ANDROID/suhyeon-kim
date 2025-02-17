package org.sopt.and.feature.mypage

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.EmptyBox
import org.sopt.and.core.designsystem.component.ProfileBox
import org.sopt.and.core.designsystem.component.TicketBox
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun MyScreen(
    navController: NavController,
    viewModel: MyViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("loginToken", "").orEmpty()

    viewModel.getUserHobby(token = token)

    val hobby by viewModel.hobby.observeAsState("")

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
                    userEmail = hobby,
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