package com.cholan.authfirebaseapp

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.cholan.authfirebaseapp.screens.CursosScreen
import com.cholan.authfirebaseapp.screens.HomeScreen
import com.cholan.authfirebaseapp.screens.LoginScreen
import com.cholan.authfirebaseapp.screens.RegisterScreen

@Composable
fun AuthApp() {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()
    val startDestination = if (auth.currentUser != null) "cursos" else "login"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("cursos") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("cursos") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable("home") {
            HomeScreen(
                onNavigateToCursos = {
                    navController.navigate("cursos")
                },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        composable("cursos") {
            CursosScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("cursos") { inclusive = true }
                    }
                }
            )
        }
    }
}
