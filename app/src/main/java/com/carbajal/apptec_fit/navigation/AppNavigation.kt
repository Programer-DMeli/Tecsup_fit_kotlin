package com.carbajal.apptec_fit.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carbajal.apptec_fit.screens.ClassDetailScreen
import com.carbajal.apptec_fit.screens.ConfirmationScreen
import com.carbajal.apptec_fit.screens.HomeScreen
import com.carbajal.apptec_fit.screens.ProfileScreen
import com.carbajal.apptec_fit.screens.ReservationScreen
import com.carbajal.apptec_fit.screens.RoutinesScreen

@Composable
fun AppNavigation() {
    // Controladores de navegación de Jetpack Compose
    val navController = rememberNavController()

    // Lista de pantallas para el menú inferior
    val bottomBarScreens = listOf(
        Screen.Home,
        Screen.Reservation,
        Screen.Routines,
        Screen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            val showBottomBar = bottomBarScreens.any { it.route == currentRoute }
            if (showBottomBar) {
                NavigationBar {
                    bottomBarScreens.forEach { screen ->
                        NavigationBarItem(
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Evita acumular pantallas en el BackStack
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                screen.icon?.let { iconVector ->
                                    Icon(
                                        imageVector = iconVector,
                                        contentDescription = screen.title
                                    )
                                }
                            },
                            label = {
                                Text(screen.title)
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Rutas de navegación principal
            composable(Screen.Home.route) {
                HomeScreen(
                    onClassClick = { classId ->
                        navController.navigate(Screen.ClassDetail.createRoute(classId))
                    }
                )
            }

            composable(Screen.Reservation.route) {
                ReservationScreen()
            }

            composable(Screen.Routines.route) {
                RoutinesScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            // Flujo secundario de reserva
            composable(
                route = Screen.ClassDetail.route,
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                val classId = backStackEntry.arguments?.getString("classId") ?: ""

                ClassDetailScreen(
                    classId = classId,
                    onBackClick = { navController.popBackStack() },
                    onReserveClick = { id ->
                        // Reemplaza la pantalla de detalle para un flujo más limpio
                        navController.navigate(Screen.Confirmations.createRoute(id)) {
                            popUpTo(Screen.ClassDetail.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(
                route = Screen.Confirmations.route,
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                val classId = backStackEntry.arguments?.getString("classId") ?: ""
                ConfirmationScreen(
                    classId = classId,
                    onGoToReservationsClick = {
                        // Navega directamente a Reservas limpiando el flujo de reserva previo
                        navController.navigate(Screen.Reservation.route) {
                            popUpTo(Screen.Home.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
