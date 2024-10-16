package org.sopt.and.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    showDialog: MutableState<Boolean>,
    isEmailError: String,
    isPasswordError: String,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = { showDialog.value = false }
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