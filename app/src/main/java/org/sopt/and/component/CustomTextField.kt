package org.sopt.and.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    suffix: @Composable() (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        suffix = suffix,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = WavveTheme.colors.Gray2F,
            focusedContainerColor = WavveTheme.colors.Gray2F,
            errorContainerColor = WavveTheme.colors.Gray2F,
            cursorColor = Color.Gray,
            focusedTextColor = Color.White,
            errorTextColor = Color.Red,
            unfocusedTextColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        isError = isError,
        shape = RoundedCornerShape(5.dp),
        singleLine = true
    )
}