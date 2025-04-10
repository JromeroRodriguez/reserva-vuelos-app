package com.example.reservavuelosapp.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reservavuelosapp.screens.PantallaFormulario
import com.example.reservavuelosapp.screens.PantallaSeleccionVuelo
import com.example.reservavuelosapp.screens.PantallaResumen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "formulario") {

        composable("formulario") {
            PantallaFormulario(navController)
        }

        composable("seleccionVuelo/{nombre}/{pasaporte}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val pasaporte = backStackEntry.arguments?.getString("pasaporte").orEmpty()
            PantallaSeleccionVuelo(navController, nombre, pasaporte)
        }

        composable("resumen/{nombre}/{pasaporte}/{destino}/{fecha}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val pasaporte = backStackEntry.arguments?.getString("pasaporte").orEmpty()
            val destino = backStackEntry.arguments?.getString("destino").orEmpty()
            val fecha = backStackEntry.arguments?.getString("fecha").orEmpty()

            PantallaResumen(
                navController = navController,
                nombre = nombre,
                pasaporte = pasaporte,
                destino = destino,
                fecha = fecha
            )
        }
    }
}
