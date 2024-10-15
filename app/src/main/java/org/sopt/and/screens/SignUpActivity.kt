package org.sopt.and.screens

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.sopt.and.R
import org.sopt.and.component.AuthTextField
import org.sopt.and.component.SocialLoginButtonGroup
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.utils.AuthKey.PASSWORD_MAX_LENGTH
import org.sopt.and.utils.AuthKey.PASSWORD_MIN_LENGTH
import org.sopt.and.utils.AuthKey.PASSWORD_PATTERN

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignUpScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen() {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }

    var isEmailError by remember { mutableStateOf("") }
    var isPasswordError by remember { mutableStateOf("") }

    val showDialog = remember { mutableStateOf(false) }

    // 이메일 및 비밀번호 검증 함수
    fun validateInputs() {
        isEmailError = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "유효한 이메일 형식이 아닙니다."
        } else {
            ""
        }

        isPasswordError = if (!isValidPassword(password)) {
            "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용해야 합니다."
        } else {
            ""
        }
    }

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
                            Icons.Sharp.Close,
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
                Row {
                    Text(
                        "이메일과 비밀번호",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Text(
                        "만으로",
                        fontSize = 20.sp,
                        color = Color.Gray,
                    )
                }
                Row {
                    Text(
                        "Wavve를 즐길 수",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Text(
                        " 있어요!",
                        fontSize = 20.sp,
                        color = Color.Gray,
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                //이메일
                AuthTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = email,
                    onValueChange = {
                        email = it
                        validateInputs() //검증
                    },
                    placeholder = stringResource(R.string.PlaceholderEmail),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    ),
                    isError = if (isEmailError.isNotEmpty()) true else false,
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
                    value = password,
                    onValueChange = {
                        password = it
                        validateInputs() //검증
                    },
                    placeholder = stringResource(R.string.PlaceholderPassword),
                    suffix = {
                        Text(
                            if (showPassword.value) stringResource(R.string.Hide) else stringResource(
                                R.string.Show
                            ),
                            color = Color.White,
                            modifier = Modifier.clickable {
                                showPassword.value = !showPassword.value
                            })
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                    isError = if (isPasswordError.isNotEmpty()) true else false
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
                SocialLoginButtonGroup(stringResource(R.string.SocialSignUp), modifier = Modifier.padding(horizontal = 20.dp))

                Spacer(modifier = Modifier.weight(1f))

                //회원가입 버튼
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = WavveTheme.colors.Gray71)
                        .clickable(
                            //ripple 효과 제거
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            validateInputs() //검증
                            if (isEmailError.isEmpty() && isPasswordError.isEmpty()) {
                                //검증 성공
                                showDialog.value = false

                                //회원가입 정보 저장, LoginActivity로 넘어가기
                                Intent(context, LoginActivity::class.java).apply {
                                    putExtra("email", email)
                                    putExtra("password", password)
                                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    (context as SignUpActivity).setResult(RESULT_OK, this)
                                    context.startActivity(this)
                                }

                            } else {
                                //검증 실패
                                showDialog.value = true
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

                    if (showDialog.value) {
                        ShowErrorDialog(showDialog, isEmailError, isPasswordError)
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowErrorDialog(
    showDialog: MutableState<Boolean>,
    isEmailError: String,
    isPasswordError: String
) {
    Dialog(
        onDismissRequest = { showDialog.value = false }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                color = Color.Black,
                text = when {
                    isEmailError.isNotEmpty() && isPasswordError.isNotEmpty() -> "$isEmailError\n$isPasswordError"
                    isEmailError.isNotEmpty() -> isEmailError
                    isPasswordError.isNotEmpty() -> isPasswordError
                    else -> ""
                }
            )
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = { showDialog.value = false },
                enabled = true
            ) {
                Text(stringResource(R.string.OK), color = Color.White)
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

//비밀번호 검증 함수
fun isValidPassword(password: String): Boolean {
    if (password.length < PASSWORD_MIN_LENGTH || password.length > PASSWORD_MAX_LENGTH) return false

    val hasUpperCase = password.any { it.isUpperCase() }
    val hasLowerCase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { PASSWORD_PATTERN.contains(it) }

    val complexityCount = listOf(hasUpperCase, hasLowerCase, hasDigit, hasSpecialChar).count { it }

    return complexityCount >= 3
}