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
    //1. Controladores de navegacion de Jetpack Compose
    val navController = rememberNavController()

    //2.- Definimos la lista de pantallas que apareceran en el menu inferior BottomBar
    val bottomBarScreens = listOf(
        Screen.Home,
        Screen.Reservation,
        Screen.Routines,
        Screen.Profile
    )

    //3.- Obtenemos la ruta actual para saber que pantalla esta seleccionada
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    //4.- Scaffold que envuelve toda la estructura con la barra inferior
    Scaffold(
        bottomBar = {
            //Solo mostrar con bottomBar SI LA PANTALLA ACTUAL PERTENECE A LA NAVEGACION Inferior
            val showBottomBar = bottomBarScreens.any { it.route == currentRoute }
            if (showBottomBar) {
                NavigationBar {
                    bottomBarScreens.forEach { screen ->
                        NavigationBarItem(
                            // Evaluamos si la ruta actual es igual a la ruta que se muestra
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    //Evita acumular una pila gigante de pantalla al hacer clic
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    //Mantener el estado de la pantalla seleccionada
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
        //5.- El NavHost registra todas las rutas de la app y que mostrar en cada una
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route, //Es la primera pantalla
            modifier = Modifier.padding(innerPadding) //aplica el margen para no tapar con la barra inferior
        ) {
            //6.- Rutas definidas para el menu Inferior
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

            //Flujo secuencial de Navegacion

            //Detalle de clase
            composable(
                route = Screen.ClassDetail.route,
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                val classId = backStackEntry.arguments?.getString("classId") ?: ""

                ClassDetailScreen(
                    classId = classId,
                    onBackClick = { navController.popBackStack() },
                    onReserveClick = { id ->
                        navController.navigate(Screen.Confirmations.createRoute(id))
                    }
                )
            }

            //Confirmacion de cupo
            composable(
                route = Screen.Confirmations.route,
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                val classId = backStackEntry.arguments?.getString("classId") ?: ""
                ConfirmationScreen(
                    classId = classId,
                    onGoToReservationsClick = {
                        navController.navigate(Screen.Reservation.route) {
                            popUpTo(Screen.Home.route)
                        }
                    }
                )
            }
        }
    }
}
