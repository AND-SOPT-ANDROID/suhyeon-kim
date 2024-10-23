package org.sopt.and.presentation.component

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.data.model.TodayTopData
import org.sopt.and.presentation.utils.AuthKey.DEFAULT_NAME
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.viewmodel.MyViewModel

@Composable
fun ProfileBox(viewModel: MyViewModel = viewModel()) {
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
                    contentDescription = stringResource(R.string.profile),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                )

                Text(
                    text = viewModel.userEmail.value ?: DEFAULT_NAME,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )

                Spacer(modifier = Modifier.weight(1f))


                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = stringResource(R.string.notifications),
                    tint = Color.White
                )

                Icon(
                    Icons.Outlined.Settings,
                    contentDescription = stringResource(R.string.settings),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                stringResource(R.string.first_buy_benefit),
                color = Color.Gray,
                modifier = Modifier.padding(start = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(start = 20.dp)
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
}

@Composable
fun TicketBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = WavveTheme.colors.Gray25)
            .padding(top = 5.dp, bottom = 15.dp)
    ) {
        Column {
            Text(
                stringResource(R.string.no_ticket),
                color = Color.Gray,
                modifier = Modifier.padding(start = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(start = 20.dp)
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
}

@Composable
fun EmptyBox(title: String, subTitle: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            title,
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
            subTitle,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EditorRecommendBox(topData: TodayTopData, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 110.dp, height = 160.dp)
            .padding(top = 10.dp)
    ) {
        Image(
            painter = painterResource(id = topData.painterId),
            contentDescription = "에디터 추천작",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TodayTop20Box(topData: TodayTopData, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 190.dp, height = 290.dp)
            .padding(top = 10.dp)
    ) {
        Image(
            painter = painterResource(id = topData.painterId),
            contentDescription = "Top 20 이미지",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = topData.ranking.toString(),
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 10.dp, y = 30.dp),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black,
                    blurRadius = 4f,
                    offset = Offset(x = 2f, y = 2f)
                )
            )
        )
    }
}
