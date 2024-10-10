package org.sopt.and

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.ui.theme.ANDANDROIDTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val localEmail = intent.getStringExtra("email") ?: "123"
        val localPassword = intent.getStringExtra("password") ?: "123"

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                LoginScreen(localEmail, localPassword)
                Log.d("localdataResult", "$localEmail, $localPassword")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun LoginScreen(localEmail: String, localPassword: String) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
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
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            //뒤로가기
                        }) {
                        Icon(
                            Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF1B1B1B))
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF1B1B1B))
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                //이메일
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("이메일 주소 또는 아이디") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFF2F2F2F),
                        focusedContainerColor = Color(0xFF2F2F2F),
                        cursorColor = Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.Gray,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(5.dp),
                    maxLines = 1,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(5.dp))

                //비밀번호
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("비밀번호") },
                    suffix = {
                        Text(
                            if (showPassword.value) "hide" else "show",
                            color = Color.White,
                            modifier = Modifier.clickable {
                                showPassword.value = !showPassword.value
                            })
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFF2F2F2F),
                        focusedContainerColor = Color(0xFF2F2F2F),
                        cursorColor = Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.Gray,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(5.dp),
                    maxLines = 1,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(30.dp))

                //기본 로그인 버튼
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1353FA)
                    ),
                    shape = RoundedCornerShape(size = 50.dp),
                    onClick = {
                        if (email == localEmail && password == localPassword) {
                            //로그인 성공
                            Intent(context, MyActivity::class.java).apply {
                                putExtra("email", email)
                                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                context.startActivity(this)
                            }
                        } else {
                            //로그인 실패
                            scope.launch {
                                snackbarHostState.showSnackbar("로그인에 실패했습니다.")
                            }
                        }

                        //키보드 내리기
                        keyboardController?.hide()
                    },
                    ) {
                    Text(
                        text = "로그인",
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
                            "아이디 찾기",
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                        VerticalDivider(
                            thickness = 1.dp,
                            color = Color.DarkGray
                        )
                        Text(
                            "비밀번호 재설정",
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                        VerticalDivider(
                            thickness = 1.dp,
                            color = Color.DarkGray
                        )
                        Text(
                            "회원가입",
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                //소셜 로그인 버튼
                SocialLoginButton()
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }

    }
}

@Composable
private fun SocialLoginButton() {
    //구분선
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp),
            color = Color.DarkGray
        )
        Text("또는 다른 서비스 계정으로 로그인", color = Color.Gray)

        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp),
            color = Color.DarkGray
        )
    }

    Spacer(modifier = Modifier.height(30.dp))

    //소셜 로그인 버튼
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_apple),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_apple),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(30.dp))

    Row {
        Text(
            "ㆍ",
            fontSize = 12.sp,
            color = Color.Gray,
        )
        Text(
            "SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다.\n기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요",
            fontSize = 12.sp,
            color = Color.Gray,
        )
    }
}