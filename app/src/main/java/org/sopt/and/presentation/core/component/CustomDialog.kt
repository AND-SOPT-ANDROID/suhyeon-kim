package org.sopt.and.presentation.core.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.sopt.and.R

@Composable
fun ErrorDialog(
    onDismissRequest: () -> Unit,
    onClick: () -> Unit,
    isEmailError: String,
    isPasswordError: String,
    isHobbyError: String,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = modifier,
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
                    isEmailError.isNotEmpty() && isPasswordError.isNotEmpty() && isHobbyError.isNotEmpty() ->
                        "$isEmailError\n$isPasswordError\n$isHobbyError"

                    isEmailError.isNotEmpty() && isPasswordError.isNotEmpty() ->
                        "$isEmailError\n$isPasswordError"

                    isEmailError.isNotEmpty() && isHobbyError.isNotEmpty() ->
                        "$isEmailError\n$isHobbyError"

                    isPasswordError.isNotEmpty() && isHobbyError.isNotEmpty() ->
                        "$isPasswordError\n$isHobbyError"

                    isEmailError.isNotEmpty() -> isEmailError
                    isPasswordError.isNotEmpty() -> isPasswordError
                    isHobbyError.isNotEmpty() -> isHobbyError
                    else -> ""
                }
            )
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = onClick,
                enabled = true
            ) {
                Text(stringResource(R.string.ok), color = Color.White)
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}