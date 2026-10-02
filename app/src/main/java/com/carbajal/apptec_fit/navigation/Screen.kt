package com.carbajal.apptec_fit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector? = null //Icono para la BottomBar opcional

) {

    //Route para las pantallas de la BottomBar

    object Home : Screen(
        route = "home",
        title = "Inicio",
        icon = Icons.Default.Home
    )

    object Reservation : Screen(
        route = "reservations",
        title = "Reservas",
        icon = Icons.Default.DateRange

    )

    object Routines : Screen(
        route = "routines",
        title = "Rutinas",
        icon = Icons.Default.List
    )

    object Profile : Screen(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )

    //Routes para pantallas Secuenciales

    //Al ser pantallas con datos dinamicos se pueden usar object con funciones

    //Detalle de clase:

    object ClassDetail : Screen(
        route = "class_detail/{classId}",
        title = "Detalle de clase"
    ){
        fun createRoute(classId: String) = "class_detail/$classId"
    }

    //Confirmaciones: Recibe el ID de la clase para Mostrar el resumen

    object Confirmations : Screen(
        route = "confirmations/{classId}",
        title = "Confirmaciones"
    ){
        fun createRoute(classId: String) = "confirmations/$classId"  //funciones para construir rutas personalizadas.
    }

}