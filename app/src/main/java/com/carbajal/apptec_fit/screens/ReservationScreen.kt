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
import com.carbajal.apptec_fit.data.Reservation

@Composable
fun ReservationScreen() {
    // Datos simulados para las reservas del usuario
    val dummyReservations = listOf(
        Reservation(
            id = "res_1",
            className = "Yoga funcional",
            date = "Hoy",
            time = "07:00 AM",
            status = "Confirmada"
        ),
        Reservation(
            id = "res_2",
            className = "Spinning",
            date = "Ayer",
            time = "06:00 PM",
            status = "Completada"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // --- ENCABEZADO ---
        Text(
            text = "Mis Reservas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Revisa el estado de tus clases agendadas",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        // --- LISTA DE RESERVAS (LazyColumn) ---
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(dummyReservations) { reservation ->
                ReservationCard(reservation = reservation)
            }
        }
    }
}

// Componente individual para renderizar la tarjeta de cada reserva
@Composable
fun ReservationCard(reservation: Reservation) {
    // Lógica para definir el color según el estado
    val isConfirmed = reservation.status == "Confirmada"
    val statusBgColor = if (isConfirmed) Color(0xFFE8F5E9) else Color(0xFFF5F5F5)
    val statusTextColor = if (isConfirmed) Color(0xFF2E7D32) else Color(0xFF616161)

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
                    text = reservation.className,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                // Badge de estado (Confirmada vs Completada)
                Surface(
                    color = statusBgColor,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = reservation.status,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = statusTextColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${reservation.date} · ${reservation.time}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}
