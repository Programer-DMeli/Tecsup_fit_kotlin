package com.carbajal.apptec_fit.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carbajal.apptec_fit.data.FitnessClass
import com.carbajal.apptec_fit.data.FitnessRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClassClick: (String) -> Unit //Callback que envia el ID de la clase seleccionada
) {
    // 1. Estado local para saber cuál filtro está activo ("Hoy" o "Esta semana")
    var selectedFilter by remember { mutableStateOf("Hoy") }
    val filters = listOf("Hoy", "Esta semana")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Encabezado
        Text(
            text = "TECSUP Fit",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Bienvenido",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(15.dp))

        //Filtros Horizontales LazyRow
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filters) { filter ->
                val isSelected = filter == selectedFilter

                FilterChip(
                    selected = isSelected,
                    onClick = { selectedFilter = filter },
                    label = { Text(filter) },
                    shape = RoundedCornerShape(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Clases Disponibles",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        //Lista vertical de clases
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(FitnessRepository.dummyClasses) { fitnessClass ->
                FitnessClassCard(
                    fitnessClass = fitnessClass,
                    onClick = { onClassClick(fitnessClass.id) }
                )
            }
        }
    }
}

// Componente individual para renderizar la tarjeta de cada clase
@Composable
fun FitnessClassCard(
    fitnessClass: FitnessClass,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
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
                    text = fitnessClass.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                // Chip o etiqueta con el horario
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = fitnessClass.time,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${fitnessClass.room} · ${fitnessClass.duration}",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${fitnessClass.availableSpots} cupos",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (fitnessClass.availableSpots > 3) Color(0xFF2E7D32) else Color(
                        0xFFC62828
                    )
                )
            }
        }
    }
}
