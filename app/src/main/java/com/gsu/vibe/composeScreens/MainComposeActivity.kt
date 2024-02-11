package com.gsu.vibe.composeScreens

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gsu.vibe.composeScreens.screens.FocusScreen
import com.gsu.vibe.composeScreens.screens.MediaPlayerComposeScreen
import com.gsu.vibe.composeScreens.screens.MeditationScreen
import com.gsu.vibe.composeScreens.screens.NatureScreen
import com.gsu.vibe.composeScreens.screens.SleepScreen


class MainComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }


    @Composable
    fun MainScreen() {
        val navController = rememberNavController() // Создает NavHostController

        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    navController = navController
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screens.Sleep.route,
                Modifier.padding(innerPadding)
            ) {
                composable(Screens.Sleep.route) { SleepScreen(navController) }
                composable(Screens.Focus.route) { FocusScreen(navController) }
                composable(Screens.Meditation.route) { MeditationScreen(navController) }
                composable(Screens.Nature.route) { NatureScreen(navController) }
                composable(Screens.Mixer.route) { MeditationScreen(navController) }
                composable("mediaPlayerComposeScreen") {
                    Log.d("MYLogs33", "sleep")
                    MediaPlayerComposeScreen(navController)
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        val screens = listOf(
            Screens.Sleep,
            Screens.Focus,
            Screens.Meditation,
            Screens.Nature,
            Screens.Mixer
        )
        BottomNavigation(
            backgroundColor = Color(0xFF040826) // Задание цвета фона нижней панели
        ) {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            screens.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        val isSelected = currentRoute == screen.route
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null,
                            tint = if (isSelected) Color(0xFF5568F5) else Color.Gray // Пример цветов
                        )
                    },

                    label = {
                        val isSelected = currentRoute == screen.route
                        Text(
                            screen.title,
                            color = (if (isSelected) Color(0xFF5568F5) else Color.Gray),
                            fontSize = 9.sp,
                            maxLines = 1
                        )
                    },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            // Очищаем стек навигации, чтобы избежать накопления экранов
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true // Избегаем повторной загрузки экрана, если он уже загружен
                            restoreState = true // Восстановление состояния при переходе назад к экрану
                        }
                    }
                )
            }
        }
    }


}