package com.anurag.firebaseauthflow.Navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.auth.ui.AuthScreen
import com.anurag.firebaseauthflow.dashboard.DashboardNew

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Navigation_main(authVM: AuthViewModel = AuthViewModel()) {
    val currentUser by authVM.currentUser.collectAsState()
    val navController = rememberNavController()
    val startRoute = if (currentUser.status) NavigationScreens.Dashboard else NavigationScreens.Auth


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavHost(navController = navController, startDestination = startRoute.route) {
            composable("dashboard") {
                DashboardNew(authVM)
            }
            composable("auth") {
                AuthScreen(authVM)
            }
        }
    }
}