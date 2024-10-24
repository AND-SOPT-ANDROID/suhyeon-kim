package org.sopt.and

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.and.presentation.screens.Routes
import org.sopt.and.presentation.screens.home.HomeScreen
import org.sopt.and.presentation.screens.login.LoginScreen
import org.sopt.and.presentation.screens.mypage.MyScreen
import org.sopt.and.presentation.screens.search.SearchScreen
import org.sopt.and.presentation.screens.signup.SignUpScreen
import org.sopt.and.presentation.utils.noRippleClickable
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.viewmodel.MyViewModel
import org.sopt.and.viewmodel.SignUpViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val navController = rememberNavController()
                var isBottomAppBarVisible by remember { mutableStateOf(true) }
                val selected = remember {
                    mutableStateOf("홈")
                }
                val signUpViewModel: SignUpViewModel = viewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (isBottomAppBarVisible) {
                            BottomAppBar(
                                modifier = Modifier.height(110.dp),
                                containerColor = Color.Black,
                            ) {
                                NavItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Home,
                                            contentDescription = stringResource(R.string.home),
                                            modifier = Modifier.padding(bottom = 5.dp),
                                            tint = if (selected.value == "홈") Color.White else Color.Gray
                                        )
                                    },
                                    label = stringResource(R.string.home),
                                    selected = selected,
                                    modifier = Modifier
                                        .noRippleClickable {
                                            selected.value = "홈"
                                            navController.navigate(Routes.Home.screen) {
                                                popUpTo(0)
                                            }
                                        }
                                )
                                NavItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Search,
                                            contentDescription = stringResource(R.string.search),
                                            modifier = Modifier.padding(bottom = 5.dp),
                                            tint = if (selected.value == "검색") Color.White else Color.Gray
                                        )
                                    },
                                    label = stringResource(R.string.search),
                                    selected = selected,
                                    modifier = Modifier
                                        .noRippleClickable {
                                            selected.value = "검색"
                                            navController.navigate(Routes.Search.screen) {
                                                popUpTo(0)
                                            }
                                        }
                                )
                                NavItem(
                                    icon = {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_launcher_background),
                                            contentDescription = stringResource(R.string.my),
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .size(30.dp)
                                        )
                                    },
                                    label = stringResource(R.string.my),
                                    selected = selected,
                                    modifier = Modifier
                                        .noRippleClickable {
                                            selected.value = "MY"
                                            navController.navigate(Routes.My.screen) {
                                                popUpTo(0)
                                            }
                                        }
                                )
                            }
                        }
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Login.screen,
                    ) {
                        composable(Routes.Login.screen) {
                            isBottomAppBarVisible = false
                            LoginScreen(navController = navController, viewModel = signUpViewModel)
                        }
                        composable(Routes.SignUp.screen) {
                            isBottomAppBarVisible = false
                            SignUpScreen(
                                navController = navController,
                                viewModel = signUpViewModel
                            )
                        }
                        composable(Routes.Home.screen) {
                            isBottomAppBarVisible = true
                            HomeScreen(navController = navController, viewModel = MyViewModel())
                        }
                        composable(Routes.Search.screen) {
                            isBottomAppBarVisible = true
                            SearchScreen(navController = navController, viewModel = MyViewModel())
                        }
                        composable(Routes.My.screen) {
                            isBottomAppBarVisible = true
                            MyScreen(navController = navController, viewModel = MyViewModel())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.NavItem(
    icon: @Composable () -> Unit,
    label: String,
    selected: MutableState<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon()
        Text(
            text = label,
            color = if (selected.value == label) Color.White else Color.Gray
        )
    }
}
