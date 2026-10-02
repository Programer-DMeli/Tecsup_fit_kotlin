# TECSUP Fit — Aplicación de Reserva de Clases de Gimnasio

**Estudiante**: Meliton carbajal

Aplicación móvil desarrollada con **Jetpack Compose** y **Material 3** para la gestión y reserva de clases dentro del gimnasio de TECSUP. El proyecto implementa un flujo de navegación completo respaldado por una barra de navegación inferior (`BottomBar`) y pantallas de detalle dinámicas.

---

## 🎯 Requerimientos Funcionales

1. **Catálogo de Clases Disponibles (Inicio):**
   * Visualización de clases grupales (*Yoga funcional*, *Cross Training*, *Spinning*) organizadas en tarjetas.
   * Filtros rápidos mediante chips de selección (*Hoy*, *Esta semana*).
   * Indicador de cupos disponibles por clase.

2. **Flujo Secuencial de Reserva:**
   * **Detalle de Clase:** Consulta de información extendida (sala, duración, descripción y estado de cupos) a partir del ítem seleccionado.
   * **Confirmación de Reserva:** Pantalla de resumen con comprobante visual y botón de redirección hacia las reservas activas.

3. **Navegación por Pestañas (BottomBar):**
   * **Inicio (`/home`):** Acceso al listado principal y filtros.
   * **Mis Reservas (`/reservations`):** Listado de clases agendadas diferenciando estados (*Confirmada* vs. *Completada*).
   * **Rutinas (`/routines`):** Catálogo de planes de entrenamiento recomendados según nivel de dificultad.
   * **Mi Perfil (`/profile`):** Información del estudiante y tarjetas con métricas de rendimiento (clases completadas y racha de días).
---
**Evidencia sin IA**
<img width="240" height="650" alt="Captura desde 2026-10-02 14-32-56" src="https://github.com/user-attachments/assets/4364a627-37e5-4828-a47f-e0536090faa7" />

---
**Promt para mejorar con IA**
Rol: Desarrollador Senior Android (Jetpack Compose).

Tarea: Rediseña las pantallas de mi app "TecsupFit" usando Material 3.

Requisitos:
- Paleta de colores: Azul y Celeste (Tecsup).
- Mejora la jerarquía visual usando la tipografía y los íconos de Material 3.
- Haz pequeños ajustes en la navegación para que sea más fluida y explica brevemente por qué los hiciste.

Restricciones:
- Respeta estrictamente la estructura de paquetes, archivos actual y codigo.
- Mantén el código simple y corto, sin librerías complejas.
- Agrega solo comentarios breves e indispensables en el código.

---

## 📸 Evidencias del Resultado
| 1. Inicio | 2. Detalle | 3. Confirmación |
| :---: | :---: | :---: |
| <img src="https://github.com/user-attachments/assets/0d53cc71-21eb-40f5-b5fc-e22651276c91" width="220" alt="1. Inicio" /> | <img src="https://github.com/user-attachments/assets/02f9a76c-738b-4aba-8457-ec6513c8a59a" width="220" alt="2. Detalle de Clase" /> | <img src="https://github.com/user-attachments/assets/37ac2c69-2c16-4fa1-a540-4c14e97dc2bc" width="220" alt="3. Confirmación de Reserva" /> |

| 4. Mis Reservas | 5. Rutinas | 6. Mi Perfil |
| :---: | :---: | :---: |
| <img src="https://github.com/user-attachments/assets/5a0e702f-a951-4e52-9f82-684bf97f416e" width="220" alt="4. Mis Reservas" /> | <img src="https://github.com/user-attachments/assets/3cc67065-4895-46ec-9634-4210615bde7e" width="220" alt="5. Rutinas" /> | <img src="https://github.com/user-attachments/assets/801c28ff-cc91-4f72-9d6d-c59505090767" width="220" alt="6. Mi Perfil" /> |

---

## ❓ Pregunta Teórica del Laboratorio

### Si se eligió la Opción B: ¿cómo sabe el `bottomBar` cuál ícono resaltar en cada pantalla?

El `bottomBar` identifica cuál ícono debe resaltarse evaluando en tiempo real si la **ruta actual de la pila de navegación (`navBackStackEntry`)** coincide exactamente con la **ruta asignada a cada elemento del menú**.
