package com.example.reservavuelosapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.net.Uri

@Composable
fun PantallaFormulario(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var pasaporte by remember { mutableStateOf("") }

    val nombreValido = nombre.isNotBlank()
    val pasaporteValido = pasaporte.length >= 8
    val formularioValido = nombreValido && pasaporteValido

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🛫 Formulario de Reserva de vuelos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre completo") },
                    isError = !nombreValido && nombre.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (!nombreValido && nombre.isNotEmpty()) {
                    Text(
                        "El nombre no puede estar vacío",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedTextField(
                    value = pasaporte,
                    onValueChange = { pasaporte = it },
                    label = { Text("Número de pasaporte") },
                    isError = !pasaporteValido && pasaporte.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (!pasaporteValido && pasaporte.isNotEmpty()) {
                    Text(
                        "Debe tener mínimo 8 caracteres",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val nombreEncoded = Uri.encode(nombre)
                val pasaporteEncoded = Uri.encode(pasaporte)
                navController.navigate("seleccionVuelo/$nombreEncoded/$pasaporteEncoded")
            },
            enabled = formularioValido,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Siguiente")
        }
    }
}



