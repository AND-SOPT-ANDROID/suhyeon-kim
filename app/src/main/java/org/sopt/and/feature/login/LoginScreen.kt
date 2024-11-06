package org.sopt.and.feature.login

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.AuthTextField
import org.sopt.and.core.designsystem.component.SocialLoginButtonGroup
import org.sopt.and.core.designsystem.component.WavveLoginButton
import org.sopt.and.feature.main.Routes
import org.sopt.and.feature.mypage.MyViewModel
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.utils.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val myViewModel: MyViewModel = viewModel()
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")


    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.iv_wavve_logo),
                        contentDescription = stringResource(R.string.wavve_logo),
                        modifier = Modifier
                            .size(90.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            dispatcher.onBackPressed()
                        }) {
                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                            contentDescription = stringResource(R.string.back),
                            tint = Color.White,
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = WavveTheme.colors.BackgroundGray)
            )
        },
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                //이메일
                AuthTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = email,
                    onValueChange = {
                        viewModel.changeEmail(it)
                    },
                    placeholder = stringResource(R.string.email_or_id),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    ),
                )

                Spacer(modifier = Modifier.height(5.dp))

                //비밀번호
                AuthTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = {
                        viewModel.changePassword(it)
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
                            },
                        )
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
                )

                Spacer(modifier = Modifier.height(30.dp))

                //기본 로그인 버튼
                WavveLoginButton(
                    onClick = {
                        if (email == viewModel.email.value && password == viewModel.password.value) {
                            //로그인 성공
                            myViewModel.setUserEmail(viewModel.email.value!!)
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
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(IntrinsicSize.Min)
                    ) {
                        Text(
                            stringResource(R.string.find_id),
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                        VerticalDivider(
                            thickness = 1.dp,
                            color = Color.DarkGray
                        )
                        Text(
                            stringResource(R.string.reset_password),
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                        VerticalDivider(
                            thickness = 1.dp,
                            color = Color.DarkGray
                        )
                        Text(
                            stringResource(R.string.sign_up),
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.clickable {
                                navController.navigate(Routes.SignUp.screen) {
                                    popUpTo(Routes.SignUp.screen) { inclusive = true }
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                //소셜 로그인 버튼
                SocialLoginButtonGroup(stringResource(R.string.social_description))
            }
        }

    }
}