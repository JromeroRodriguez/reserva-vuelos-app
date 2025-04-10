package com.example.reservavuelosapp.screens

import android.app.DatePickerDialog
import android.net.Uri
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*
import androidx.compose.ui.Alignment



@Composable
fun PantallaSeleccionVuelo(
    navController: NavController,
    nombre: String,
    pasaporte: String
) {
    val destinos = listOf("París", "Nueva York", "Tokio")
    var destinoSeleccionado by remember { mutableStateOf(destinos[0]) }
    var expanded by remember { mutableStateOf(false) }
    var fechaSeleccionada by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Centra horizontalmente todos los elementos
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("📍 Selección de Vuelo", style = MaterialTheme.typography.headlineMedium)

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Destino:", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(8.dp))

                Box {
                    Button(onClick = { expanded = true }) {
                        Text(destinoSeleccionado)
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        destinos.forEach { destino ->
                            DropdownMenuItem(
                                text = { Text(destino) },
                                onClick = {
                                    destinoSeleccionado = destino
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Fecha del vuelo:", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(8.dp))

                DatePickerComponent(
                    selectedDate = fechaSeleccionada,
                    onDateSelected = { fechaSeleccionada = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val destinoEncoded = Uri.encode(destinoSeleccionado)
                val fechaEncoded = Uri.encode(fechaSeleccionada)
                navController.navigate("resumen/$nombre/$pasaporte/$destinoEncoded/$fechaEncoded")
            },
            enabled = fechaSeleccionada.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reservar Vuelo")
        }
    }
}




@Composable
fun DatePickerComponent(
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, y: Int, m: Int, d: Int ->
                val formattedDate = "%02d/%02d/%04d".format(d, m + 1, y)
                onDateSelected(formattedDate)
            },
            year,
            month,
            day
        )
    }

    Button(onClick = { datePickerDialog.show() }) {
        Text(text = selectedDate.ifBlank { "Seleccionar fecha" })
    }
}



