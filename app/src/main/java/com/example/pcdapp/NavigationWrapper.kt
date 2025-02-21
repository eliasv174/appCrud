package com.example.pcdapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pcdapp.presentation.home.HomeScreen
import com.example.pcdapp.presentation.initial.InitialScreen
import com.example.pcdapp.presentation.login.LoginScreen
import com.example.pcdapp.presentation.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
    db: FirebaseFirestore
) {
    NavHost(navController = navHostController, startDestination = "initial") {
        composable("initial") {
            InitialScreen(
                navigateToLogin = { navHostController.navigate("login") },
                navigateToSignUp = { navHostController.navigate("signup") }
            )
        }
        composable("login") {
            LoginScreen(
                auth = auth,
                navigateToHome = { navHostController.navigate("home") },
                navigateBack = { navHostController.popBackStack() } // Navegación hacia atrás
            )
        }
        composable("signup") {
            SignUpScreen(
                auth = auth,
                navigateBack = { navHostController.popBackStack() } // Navegación hacia atrás
            )
        }
        composable("home") {
            HomeScreen(
                db = db,
                navigateBack = { navHostController.popBackStack() } // Navegación hacia atrás
            )
        }
    }
}