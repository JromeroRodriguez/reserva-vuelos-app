package com.example.reservavuelosapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaResumen(
    navController: NavController,
    nombre: String,
    pasaporte: String,
    destino: String,
    fecha: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("📝 Resumen de Reserva", style = MaterialTheme.typography.headlineMedium)
        HorizontalDivider()

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("👤 Nombre: $nombre", style = MaterialTheme.typography.bodyLarge)
                Text("🛂 Pasaporte: $pasaporte", style = MaterialTheme.typography.bodyLarge)
                Text("✈️ Destino: $destino", style = MaterialTheme.typography.bodyLarge)
                Text("📅 Fecha: $fecha", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.popBackStack("formulario", inclusive = false) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al inicio")
        }
    }
}


