package org.sopt.and.presentation.signup

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.domain.model.request.UserSignUpModel
import org.sopt.and.presentation.core.component.AuthTextField
import org.sopt.and.presentation.core.component.ErrorDialog
import org.sopt.and.presentation.core.component.WavveSignUpButton
import org.sopt.and.ui.theme.WavveTheme
import org.sopt.and.utils.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    onSignUpSuccess: (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
    val context = LocalContext.current

    val userName by viewModel.userName.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val hobby by viewModel.hobby.observeAsState("")

    val showPassword = remember { mutableStateOf(false) }
    val showDialog by viewModel.showDialog.observeAsState(false)

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.sign_up),
                        fontSize = 18.sp,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = {
                        //뒤로가기
                        dispatcher.onBackPressed()
                    }) {
                        Icon(
                            imageVector = Icons.Sharp.Close,
                            contentDescription = stringResource(R.string.back),
                            tint = Color.White,
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = WavveTheme.colors.BackgroundGray)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = WavveTheme.colors.BackgroundGray)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(stringResource(R.string.email_and_password))
                        }
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append(stringResource(R.string.only))
                        }
                    },
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(stringResource(R.string.wavve_enjoy))
                        }
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append(stringResource(R.string.able))
                        }
                    },
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                AuthTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = userName,
                    onValueChange = {
                        viewModel.setUserName(it)
                        viewModel.validateInputs(userName, password, hobby, context)
                    },
                    placeholder = stringResource(R.string.placeholder_user_name),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    ),
                    isError = viewModel.nameErrorMsg.isNotEmpty(),
                )

                Spacer(modifier = Modifier.height(10.dp))

                WavveToolTip(stringResource(R.string.helper_text_user_name))

                Spacer(modifier = Modifier.height(20.dp))

                AuthTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = password,
                    onValueChange = {
                        viewModel.setPassword(it)
                        viewModel.validateInputs(userName, password, hobby, context)
                    },
                    placeholder = stringResource(R.string.placeholder_password),
                    suffix = {
                        Text(
                            if (showPassword.value) stringResource(R.string.hide) else stringResource(
                                R.string.show
                            ),
                            color = Color.White,
                            modifier = Modifier.noRippleClickable {
                                showPassword.value = !showPassword.value
                            }
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    ),
                    visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                    isError = viewModel.passwordErrorMsg.isNotEmpty()
                )

                Spacer(modifier = Modifier.height(10.dp))

                WavveToolTip(stringResource(R.string.helper_text_password))

                Spacer(modifier = Modifier.height(20.dp))

                AuthTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = hobby,
                    onValueChange = {
                        viewModel.setHobby(it)
                        viewModel.validateInputs(userName, password, hobby, context)
                    },
                    placeholder = stringResource(R.string.placeholder_hobby),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    isError = viewModel.hobbyErrorMsg.isNotEmpty(),
                )

                Spacer(modifier = Modifier.height(10.dp))

                WavveToolTip(stringResource(R.string.helper_text_hobby))

                Spacer(modifier = Modifier.weight(1f))

                WavveSignUpButton(
                    onClick = {
                        viewModel.onSignUpClick(
                            localName = userName,
                            localPassword = password,
                            localHobby = hobby,
                            onSuccess = { userName, password, hobby ->
                                viewModel.postUserSignUp(
                                    context = context,
                                    body = UserSignUpModel(
                                        username = userName,
                                        password = password,
                                        hobby = hobby
                                    )
                                )
                                onSignUpSuccess(userName, password)
                            },
                            onFailure = {
                                viewModel.setDialogState(true)
                            },
                            context = context
                        )
                    }
                )

                if (showDialog) {
                    ErrorDialog(
                        onDismissRequest = {
                            viewModel.setDialogState(false)
                        },
                        onClick = {
                            viewModel.setDialogState(false)
                        },
                        isEmailError = viewModel.nameErrorMsg,
                        isPasswordError = viewModel.passwordErrorMsg,
                        isHobbyError = viewModel.hobbyErrorMsg,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                    )
                }
            }
        }
    }
}

@Composable
fun WavveToolTip(description: String, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Icon(
            Icons.Outlined.Info,
            contentDescription = stringResource(R.string.info),
            tint = Color.Gray,
        )
        Text(description, color = Color.Gray)
    }
}