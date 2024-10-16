package org.sopt.and.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun SocialLoginButtonGroup(title: String, modifier: Modifier = Modifier) {
    //구분선
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                color = Color.DarkGray
            )
            Text(title, color = Color.Gray)

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

                repeat(5) {
                    SocialLoginButton(
                        painterId = R.drawable.ic_apple,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
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
                stringResource(R.string.SocialLoginDescription),
                fontSize = 12.sp,
                color = Color.Gray,
            )
        }
    }
}

@Composable
fun SocialLoginButton(@DrawableRes painterId: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = painterId),
        contentDescription = null,
        modifier = modifier
    )
}