package org.sopt.and.presentation.screens.login

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import org.sopt.and.R
import org.sopt.and.presentation.component.AuthTextField
import org.sopt.and.presentation.component.SocialLoginButtonGroup
import org.sopt.and.presentation.component.WavveLoginButton
import org.sopt.and.presentation.screens.Routes
import org.sopt.and.presentation.utils.noRippleClickable
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.viewmodel.MyViewModel
import org.sopt.and.viewmodel.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: SignUpViewModel) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val myViewModel: MyViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


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
                        email = it
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
                        password = it
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
                    email,
                    viewModel,
                    password,
                    myViewModel,
                    navController,
                    scope,
                    snackbarHostState,
                    context,
                    focusManager
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