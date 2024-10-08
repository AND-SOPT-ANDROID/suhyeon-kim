package org.sopt.and

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                MyScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
                //프로필 박스
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF252525))
                        .padding(vertical = 20.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(50.dp)
                            )

                            Text(
                                "프로필1님",
                                color = Color.White,
                                modifier = Modifier.padding(start = 10.dp)
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(
                                onClick = {
                                    //알림
                                }) {
                                Icon(
                                    Icons.Outlined.Notifications,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                            IconButton(
                                onClick = {
                                    //세팅
                                }) {
                                Icon(
                                    Icons.Outlined.Settings,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            "첫 결제 시 첫 달 100원!",
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Text("구매하기", color = Color.White)
                            Icon(
                                Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(1.dp))

                //이용권 박스
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF252525))
                        .padding(top = 5.dp, bottom = 15.dp)
                ) {
                    Column {
                        Text(
                            "현재 보유하신 이용권이 없습니다.",
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Text("구매하기", color = Color.White)
                            Icon(
                                Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                //전체 시청 내역
                Text(
                    "전체 시청내역",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(50.dp))

                Icon(
                    Icons.Outlined.Info,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp)
                )
                Text(
                    "시청내역이 없어요.",
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))

                //관심 프로그램
                Text(
                    "관심 프로그램",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Icon(
                    Icons.Outlined.Info,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp)
                )
                Text(
                    "관심 프로그램이 없어요.",
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp),
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}