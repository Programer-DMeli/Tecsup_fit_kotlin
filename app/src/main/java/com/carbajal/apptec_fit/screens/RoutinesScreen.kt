package com.carbajal.apptec_fit.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Estructura local sencilla para representar una rutina
data class Routine(
    val id: String,
    val title: String,
    val category: String,
    val duration: String,
    val level: String
)

@Composable
fun RoutinesScreen() {
    // Lista de rutinas simuladas
    val dummyRoutines = listOf(
        Routine("r1", "Rutina de Torso & Brazos", "Fuerza", "45 min", "Intermedio"),
        Routine("r2", "Quema de Calorías Hiit", "Cardio", "30 min", "Avanzado"),
        Routine("r3", "Flexibilidad y Estiramiento", "Movilidad", "20 min", "Principiante")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // --- ENCABEZADO ---
        Text(
            text = "Rutinas de Entrenamiento",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Explora los planes preparados por los entrenadores",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        // --- LISTA DE RUTINAS ---
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(dummyRoutines) { routine ->
                RoutineCard(routine = routine)
            }
        }
    }
}

@Composable
fun RoutineCard(routine: Routine) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = routine.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = routine.level,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${routine.category} · ${routine.duration}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}
