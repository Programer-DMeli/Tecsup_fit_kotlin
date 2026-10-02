package com.carbajal.apptec_fit.data

//Se genera una clase especial diseñada unicamente para contener datos simulando una base de datos

data class FitnessClass(
    val id:String,
    val name: String,
    val time: String,
    val room: String,
    val duration: String,
    val availableSpots: Int  //numero de cupos disponibles

)
//Reserva usuario

data class Reservation(
    val id: String,
    val className: String,
    val date: String, //fecha de la reserva
    val time: String, //hora de la reserva
    val status: String  //estado actual
)

// 3. Contenedor de datos simulados
object FitnessRepository {

    // Lista estática con las 3 clases requeridas
    val dummyClasses = listOf(
        FitnessClass(
            id = "class_1",
            name = "Yoga funcional",
            time = "07:00 AM",
            room = "Sala 1",
            duration = "45 min",
            availableSpots = 8
        ),
        FitnessClass(
            id = "class_2",
            name = "Cross Training",
            time = "09:00 AM",
            room = "Zona Exterior",
            duration = "60 min",
            availableSpots = 3
        ),
        FitnessClass(
            id = "class_3",
            name = "Spinning",
            time = "06:00 PM",
            room = "Sala Ciclismo",
            duration = "50 min",
            availableSpots = 5
        )
    )

    // Función auxiliar para buscar una clase por su ID (Servirá para la pantalla de Detalle)
    fun getClassById(classId: String): FitnessClass? {
        return dummyClasses.find { it.id == classId }
    }
}