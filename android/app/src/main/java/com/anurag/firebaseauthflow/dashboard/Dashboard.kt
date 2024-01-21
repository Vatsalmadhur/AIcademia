package com.anurag.firebaseauthflow.dashboard

import FirebaseAuthFlowTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.common.AppBar.TopBar
import com.anurag.firebaseauthflow.common.BottomNav.BottomNavigation
import com.anurag.firebaseauthflow.dashboard.Navigation.Navigation_dash

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardNew(authVM: AuthViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    FirebaseAuthFlowTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(topBar = {
                TopBar(drawerState)
            }, bottomBar = {
                BottomNavigation(navController)
            }) {
                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet {
                            NavigationDrawerItem(label = { Text(text = "Introduction") },
                                selected = false,
                                onClick = {})
                            NavigationDrawerItem(label = { Text(text = "Today's goals") },
                                selected = false,
                                onClick = {})
                            NavigationDrawerItem(label = { Text(text = "Expert's Advice") },
                                selected = false,
                                onClick = {})
                            NavigationDrawerItem(label = { Text(text = "Resources") },
                                selected = false,
                                onClick = {})
                            NavigationDrawerItem(label = { Text(text = "Logout") },
                                selected = false,
                                onClick = { authVM.logout() })
                        }
                    },
                    drawerState = drawerState,
                    gesturesEnabled = true,
                    modifier = Modifier
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = it.calculateBottomPadding()
                        )
                ) {
                    Navigation_dash(navController = navController, authVM)
                }
            }
        }
    }
}