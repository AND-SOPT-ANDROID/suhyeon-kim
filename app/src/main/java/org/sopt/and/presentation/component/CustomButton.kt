package org.sopt.and.presentation.component

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.presentation.screens.Routes
import org.sopt.and.presentation.utils.noRippleClickable
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.viewmodel.MyViewModel
import org.sopt.and.viewmodel.SignUpViewModel

@Composable
fun BuyGuideButton(
    subTitle: String, modifier: Modifier = Modifier
) {
    Text(
        subTitle,
        color = Color.Gray,
        modifier = modifier.padding(start = 20.dp)
    )
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(start = 20.dp)
    ) {
        Text(stringResource(R.string.buy), color = Color.White)
        Icon(
            Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = stringResource(R.string.buy),
            tint = Color.White
        )
    }
}

@Composable
fun WavveSignUpButton(
    viewModel: SignUpViewModel,
    email: String,
    password: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = WavveTheme.colors.Gray71)
            .noRippleClickable {
                viewModel.validateInputs(email = email, password = password) //검증
                if (viewModel.emailErrorMsg.isEmpty() && viewModel.passwordErrorMsg.isEmpty()) {
                    //검증 성공
                    viewModel.showDialog.value = false

                    //회원가입 정보 저장
                    viewModel.changeEmail(email)
                    viewModel.changePassword(password)
                    navController.navigate(Routes.Login.screen) {
                        popUpTo(Routes.Login.screen) { inclusive = true }
                    }
                } else {
                    //검증 실패
                    viewModel.showDialog.value = true
                }
            },
    ) {
        Text(
            stringResource(R.string.sign_up_button),
            modifier = Modifier
                .padding(vertical = 18.dp)
                .align(Alignment.Center),
            color = Color.White,
            fontSize = 18.sp,
        )

        if (viewModel.showDialog.value) {
            ErrorDialog(
                showDialog = viewModel.showDialog,
                isEmailError = viewModel.emailErrorMsg,
                isPasswordError = viewModel.passwordErrorMsg,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
            )
        }
    }
}

@Composable
fun WavveLoginButton(
    email: String,
    viewModel: SignUpViewModel,
    password: String,
    myViewModel: MyViewModel,
    navController: NavController,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    context: Context,
    focusManager: FocusManager,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = WavveTheme.colors.VividBlue
        ),
        shape = RoundedCornerShape(size = 50.dp),
        onClick = {
            if (email == viewModel.email && password == viewModel.password) {
                //로그인 성공
                myViewModel.setUserEmail(viewModel.email)
                navController.navigate(Routes.Home.screen) {
                    popUpTo(Routes.Home.screen) { inclusive = true }
                }
            } else {
                //로그인 실패
                scope.launch {
                    snackbarHostState.showSnackbar(context.getString(R.string.fail_to_login))
                }
            }

            //키보드 내리기
            focusManager.clearFocus()
        },
    ) {
        Text(
            text = stringResource(R.string.login),
            color = Color.White
        )
    }
}