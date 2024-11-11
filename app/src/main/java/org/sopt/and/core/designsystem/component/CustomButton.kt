package org.sopt.and.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.utils.noRippleClickable

@Composable
fun BuyGuideButton(
    subTitle: String, modifier: Modifier = Modifier
) {
    Column {
        Text(
            subTitle,
            color = Color.Gray,
            modifier = modifier.padding(start = 20.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier.padding(start = 20.dp)
        ) {
            Text(stringResource(R.string.buy), color = Color.White)
            Icon(
                Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = stringResource(R.string.buy),
                tint = Color.White
            )
        }
    }
}

@Composable
fun WavveSignUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = WavveTheme.colors.Gray71)
            .noRippleClickable {
                onClick()
            },
    ) {
        Text(
            stringResource(R.string.sign_up_button),
            modifier = Modifier
                .padding(vertical = 18.dp)
                .align(Alignment.Center),
            color = Color.White,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun WavveLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = WavveTheme.colors.VividBlue
        ),
        shape = RoundedCornerShape(size = 50.dp),
        onClick = onClick,
    ) {
        Text(
            text = stringResource(R.string.login),
            color = Color.White
        )
    }
}