package org.sopt.and.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.component.EmptyBox
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.utils.AuthKey.EMAIL

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val localEmail = intent.getStringExtra(EMAIL) ?: "프로필1님"

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                MyScreen(localEmail)
            }
        }
    }
}

@Composable
fun MyScreen(localEmail: String) {
    val snackbarHostState = remember { SnackbarHostState() }

    //화면 진입 시 snackBar 표시
    LaunchedEffect(Unit) {
        snackbarHostState.showSnackbar("환영합니다!")
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = WavveTheme.colors.BackgroundGray)
                .padding(innerPadding)
        ) {
            Column {
                //프로필 박스
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = WavveTheme.colors.Gray25)
                        .padding(vertical = 20.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = stringResource(R.string.Profile),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(50.dp)
                            )

                            Text(
                                localEmail,
                                color = Color.White,
                                modifier = Modifier.padding(start = 10.dp)
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(
                                onClick = {
                                }) {
                                Icon(
                                    Icons.Outlined.Notifications,
                                    contentDescription = stringResource(R.string.Notifications),
                                    tint = Color.White
                                )
                            }
                            IconButton(
                                onClick = {
                                }) {
                                Icon(
                                    Icons.Outlined.Settings,
                                    contentDescription = stringResource(R.string.Settings),
                                    tint = Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            stringResource(R.string.FirstBuyBenefit),
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Text(stringResource(R.string.Buy), color = Color.White)
                            Icon(
                                Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                contentDescription = stringResource(R.string.Buy),
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
                        .background(color = WavveTheme.colors.Gray25)
                        .padding(top = 5.dp, bottom = 15.dp)
                ) {
                    Column {
                        Text(
                            stringResource(R.string.NoTicket),
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Text(stringResource(R.string.Buy), color = Color.White)
                            Icon(
                                Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                contentDescription = stringResource(R.string.Buy),
                                tint = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                //전체 시청 내역
                EmptyBox(
                    stringResource(R.string.ViewingHistory),
                    stringResource(R.string.NoViewingHistory),
                    )

                Spacer(modifier = Modifier.height(30.dp))

                //관심 프로그램
                EmptyBox(
                    stringResource(R.string.InterestedProgram),
                    stringResource(R.string.NoInterestedProgram),
                )
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
