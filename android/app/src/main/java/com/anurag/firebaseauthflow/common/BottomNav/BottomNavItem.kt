package com.anurag.firebaseauthflow.common.BottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.anurag.firebaseauthflow.dashboard.Navigation.Screens

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var route: String
) {
    object Home :
        BottomNavItem(
            "Home",
            Icons.Default.Home,
            Screens.Home.route
        )

    object Doubts :
        BottomNavItem(
            "Doubts",
            Icons.Default.List,
            Screens.Doubts.route
        )


    object Profile :
        BottomNavItem(
            "Profile",
            Icons.Default.Person,
            Screens.Profile.route
        )
}