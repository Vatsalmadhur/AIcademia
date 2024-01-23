package com.anurag.firebaseauthflow.common.BottomNav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home, BottomNavItem.Doubts, BottomNavItem.Profile
    )
    NavigationBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            .navigationBarsPadding()
    ) {
        items.forEach { item ->
            AddItem(
                screen = item, navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem, navController: NavHostController
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                screen.icon, contentDescription = screen.title
            )
        },
        selected = screen.route == backStackEntry.value?.destination?.route,
        alwaysShowLabel = true,
        onClick = {
            navController.navigate(screen.route)
        },
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = MaterialTheme.colorScheme.onBackground,
            indicatorColor = MaterialTheme.colorScheme.surface,
            selectedIconColor = MaterialTheme.colorScheme.onSurface,
        ),
    )
}