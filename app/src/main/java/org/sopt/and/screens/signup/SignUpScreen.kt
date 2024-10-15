package org.sopt.and.screens.signup

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.component.AuthTextField
import org.sopt.and.component.ShowErrorDialog
import org.sopt.and.component.SocialLoginButtonGroup
import org.sopt.and.screens.Routes
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.utils.noRippleClickable
import org.sopt.and.viewmodel.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = viewModel()) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.SignUp),
                        fontSize = 18.sp,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = {
                        //뒤로가기
                        (context as? Activity)?.finish()

                    }) {
                        Icon(
                            imageVector = Icons.Sharp.Close,
                            contentDescription = stringResource(R.string.Back),
                            tint = Color.White,
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
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
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append("이메일과 비밀번호")
                        }
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("만으로")
                        }
                    },
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append("Wavve를 즐길 수")
                        }
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append(" 있어요!")
                        }
                    },
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                //이메일
                AuthTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = viewModel.email,
                    onValueChange = {
                        viewModel.email = it
                        viewModel.validateInputs() //검증
                    },
                    placeholder = stringResource(R.string.PlaceholderEmail),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    ),
                    isError = if (viewModel.emailErrorMsg.isNotEmpty()) true else false,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = stringResource(R.string.Info),
                        tint = Color.Gray,
                    )
                    Text(stringResource(R.string.EmailHelperText), color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(20.dp))

                //비밀번호
                AuthTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = viewModel.password,
                    onValueChange = {
                        viewModel.password = it
                        viewModel.validateInputs() //검증
                    },
                    placeholder = stringResource(R.string.PlaceholderPassword),
                    suffix = {
                        Text(
                            if (viewModel.showPassword.value) stringResource(R.string.Hide) else stringResource(
                                R.string.Show
                            ),
                            color = Color.White,
                            modifier = Modifier.clickable {
                                viewModel.showPassword.value = !viewModel.showPassword.value
                            })
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    visualTransformation = if (viewModel.showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                    isError = if (viewModel.passwordErrorMsg.isNotEmpty()) true else false
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = stringResource(R.string.Info),
                        tint = Color.Gray,
                    )
                    Text(
                        stringResource(R.string.PasswordHelperText),
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                //소셜 로그인
                SocialLoginButtonGroup(
                    stringResource(R.string.SocialSignUp),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                //회원가입 버튼
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = WavveTheme.colors.Gray71)
                        .noRippleClickable {
                            viewModel.validateInputs() //검증
                            if (viewModel.emailErrorMsg.isEmpty() && viewModel.passwordErrorMsg.isEmpty()) {
                                //검증 성공
                                viewModel.showDialog.value = false

                                //회원가입 정보 저장
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
                        stringResource(R.string.WavveSignUp),
                        modifier = Modifier
                            .padding(vertical = 18.dp)
                            .align(Alignment.Center),
                        color = Color.White,
                        fontSize = 18.sp,
                    )

                    if (viewModel.showDialog.value) {
                        ShowErrorDialog(
                            viewModel.showDialog,
                            viewModel.emailErrorMsg,
                            viewModel.passwordErrorMsg
                        )
                    }
                }
            }
        }
    }
}