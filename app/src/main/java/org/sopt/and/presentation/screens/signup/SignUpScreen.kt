package org.sopt.and.presentation.screens.signup

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.presentation.component.AuthTextField
import org.sopt.and.presentation.component.ErrorDialog
import org.sopt.and.presentation.component.SocialLoginButtonGroup
import org.sopt.and.presentation.component.WavveSignUpButton
import org.sopt.and.presentation.screens.Routes
import org.sopt.and.presentation.utils.noRippleClickable
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.viewmodel.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel) {
    val focusManager = LocalFocusManager.current
    val dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.sign_up),
                        fontSize = 18.sp,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = {
                        //뒤로가기
                        dispatcher.onBackPressed()
                    }) {
                        Icon(
                            imageVector = Icons.Sharp.Close,
                            contentDescription = stringResource(R.string.back),
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
                            append(stringResource(R.string.email_and_password))
                        }
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append(stringResource(R.string.only))
                        }
                    },
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(stringResource(R.string.wavve_enjoy))
                        }
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append(stringResource(R.string.able))
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
                    value = email,
                    onValueChange = {
                        email = it
                        viewModel.validateInputs(email, password) //검증
                    },
                    placeholder = stringResource(R.string.placeholder_email),
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

                WavveToolTip(stringResource(R.string.helper_text_email))

                Spacer(modifier = Modifier.height(20.dp))

                //비밀번호
                AuthTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = password,
                    onValueChange = {
                        password = it
                        viewModel.validateInputs(email, password) //검증
                    },
                    placeholder = stringResource(R.string.placeholder_password),
                    suffix = {
                        Text(
                            if (viewModel.showPassword.value) stringResource(R.string.hide) else stringResource(
                                R.string.show
                            ),
                            color = Color.White,
                            modifier = Modifier.noRippleClickable {
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

                WavveToolTip(stringResource(R.string.helper_text_password))

                Spacer(modifier = Modifier.height(50.dp))

                //소셜 로그인
                SocialLoginButtonGroup(
                    stringResource(R.string.social_description_2),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                //회원가입 버튼
                WavveSignUpButton(viewModel, email, password, navController)
            }
        }
    }
}

@Composable
fun WavveToolTip(description: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Icon(
            Icons.Outlined.Info,
            contentDescription = stringResource(R.string.info),
            tint = Color.Gray,
        )
        Text(description, color = Color.Gray)
    }
}