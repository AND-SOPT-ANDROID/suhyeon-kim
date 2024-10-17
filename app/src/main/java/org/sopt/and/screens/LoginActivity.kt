package org.sopt.and.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.component.AuthTextField
import org.sopt.and.component.SocialLoginButtonGroup
import org.sopt.and.screens.signup.SignUpActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.utils.AuthKey.EMAIL
import org.sopt.and.utils.AuthKey.PASSWORD

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val localEmail = intent.getStringExtra(EMAIL).orEmpty()
        val localPassword = intent.getStringExtra(PASSWORD).orEmpty()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                LoginScreen(localEmail, localPassword)

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(localEmail: String, localPassword: String) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

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
                            //뒤로가기
                            (context as? Activity)?.finish()
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
                            if (showPassword.value) stringResource(R.string.hide) else stringResource(
                                R.string.show
                            ),
                            color = Color.White,
                            modifier = Modifier.clickable {
                                showPassword.value = !showPassword.value
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
                    visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
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
                        if (email == localEmail && password == localPassword) {
                            //로그인 성공
                            Intent(context, MyActivity::class.java).apply {
                                putExtra(EMAIL, email)
                                flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                context.startActivity(this)
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
                        text = stringResource(R.string.login),
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
                                Intent(context, SignUpActivity::class.java).let { intent ->
                                    context.startActivity(intent)
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                //소셜 로그인 버튼
                SocialLoginButtonGroup(stringResource(R.string.social_description))
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }

    }
}