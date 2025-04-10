package com.example.reservavuelosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.reservavuelosapp.nav.AppNavigation
import com.example.reservavuelosapp.ui.theme.ReservaVuelosAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReservaVuelosAppTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}
