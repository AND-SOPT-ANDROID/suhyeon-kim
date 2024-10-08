package org.sopt.and

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
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme

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
@Preview(showBackground = true)
@Composable
fun SignUpScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("회원가입", fontSize = 18.sp, color = Color.White) },
                actions = {
                    IconButton(onClick = {
                        //뒤로가기
                    }) {
                        Icon(
                            Icons.Sharp.Close,
                            contentDescription = "close",
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
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("wavve@example.com") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
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
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = "tooltip",
                        tint = Color.Gray,
                    )
                    Text("로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해주세요.", color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(20.dp))

                //비밀번호
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Wavve 비밀번호 설정") },
                    suffix = {
                        Text(
                            "show",
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
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
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
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = "tooltip",
                        tint = Color.Gray,
                    )
                    Text(
                        "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해 주세요.",
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                //소셜 로그인
                SocialLoginButton()

                Spacer(modifier = Modifier.weight(1f))

                //회원가입 버튼
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF717171))
                        .clickable {
                            //버튼
                        },
                ) {
                    Text(
                        "Wavve 회원가입",
                        modifier = Modifier
                            .padding(vertical = 18.dp)
                            .align(Alignment.Center),
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                }
            }
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
                .padding(start = 20.dp, end = 10.dp),
            color = Color.DarkGray
        )
        Text("또는 다른 서비스 계정으로 가입", color = Color.Gray)

        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp, end = 20.dp),
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

    Row (
        modifier = Modifier.padding(horizontal = 20.dp)
    ){
        Text(
            "ㆍ",
            fontSize = 12.sp,
            color = Color.Gray,
        )
        Text(
            "SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다. 기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요",
            fontSize = 12.sp,
            color = Color.Gray,
        )
    }
}