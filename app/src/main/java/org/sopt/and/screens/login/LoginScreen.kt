package org.sopt.and.screens.login

import android.app.Activity
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import org.sopt.and.component.AuthTextField
import org.sopt.and.component.SocialLoginButtonGroup
import org.sopt.and.screens.Routes
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.viewmodel.LoginViewModel
import org.sopt.and.viewmodel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current


    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val myViewModel: MyViewModel = viewModel()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.iv_wavve_logo),
                        contentDescription = stringResource(R.string.Wavve_logo),
                        modifier = Modifier
                            .size(90.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            //뒤로가기
                            (context as? Activity)?.finish()
                        }) {
                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
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
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                //이메일
                AuthTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = viewModel.email,
                    onValueChange = {
                        viewModel.email = it
                    },
                    placeholder = stringResource(R.string.EmailOrID),
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
                    value = viewModel.password,
                    onValueChange = {
                        viewModel.password = it
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
                )


                Spacer(modifier = Modifier.height(30.dp))

                //기본 로그인 버튼
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WavveTheme.colors.VividBlue
                    ),
                    shape = RoundedCornerShape(size = 50.dp),
                    onClick = {

                        if (viewModel.email == "localEmail" && viewModel.password == "localPassword") {
                            //로그인 성공
                            myViewModel.setUserEmail(viewModel.email)
                            navController.navigate(Routes.My.screen) {
                                popUpTo(Routes.My.screen) { inclusive = true }
                            }
                        } else {
                            //로그인 실패
                            scope.launch {
                                snackbarHostState.showSnackbar("로그인에 실패했습니다.")
                            }
                        }

                        //키보드 내리기
                        focusManager.clearFocus()
                    },
                ) {
                    Text(
                        text = stringResource(R.string.Login),
                        color = Color.White
                    )
                }

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
                            stringResource(R.string.FindID),
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                        VerticalDivider(
                            thickness = 1.dp,
                            color = Color.DarkGray
                        )
                        Text(
                            stringResource(R.string.ResetPassword),
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                        VerticalDivider(
                            thickness = 1.dp,
                            color = Color.DarkGray
                        )
                        Text(
                            stringResource(R.string.SignUp),
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
                SocialLoginButtonGroup(stringResource(R.string.SocialLogin))
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }

    }
}