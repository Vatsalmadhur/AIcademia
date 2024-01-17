package com.anurag.firebaseauthflow.Navigation

sealed class NavigationScreens(val route: String) {
    object Dashboard: NavigationScreens("dashboard")
    object Auth: NavigationScreens("auth")
}