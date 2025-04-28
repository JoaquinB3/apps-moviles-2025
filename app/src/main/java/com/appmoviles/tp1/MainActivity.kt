package com.appmoviles.tp1

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appmoviles.tp1.ui.theme.Tp1Theme
import com.appmoviles.tp1.views.BienvenidaPage
import com.appmoviles.tp1.views.Register

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp1Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Login") {
                    composable("Login") { LoginScreen(navController) }
                    composable("Bienvenida/{nombre}") { backStackEntry ->
                        val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
                        BienvenidaPage(nombre)
                    }
                    composable("register") {
                        Register(navController)
                    }
                }
            }
        }
    }
}