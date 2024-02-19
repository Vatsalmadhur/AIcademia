package com.anurag.firebaseauthflow.dashboard.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anurag.firebaseauthflow.SkillChooser.priorskills.NewSkill
import com.anurag.firebaseauthflow.SkillChooser.priorskills.PriorSkills
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.dashboard.Home.Home
import com.anurag.firebaseauthflow.dashboard.doubts.Doubts
import com.anurag.firebaseauthflow.dashboard.profile.Profile
import com.anurag.firebaseauthflow.doubtForm.NewDoubt

@Composable
fun Navigation_dash(navController: NavHostController, authVM: AuthViewModel) {
    val startRoute = remember {
        mutableStateOf("home")
    }
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
        NavHost(navController = navController, startDestination = startRoute.value) {
            composable(Screens.Home.route) {
                Home(authVM, navController)
            }
            composable(Screens.Doubts.route) {
                Doubts(navController)
            }
            composable(Screens.Profile.route) {
                Profile(authVM, navController)
            }
            composable(Screens.PriorSkills.route) {
                PriorSkills(navController)
            }
            composable(Screens.NewSkill.route) {
                NewSkill(navController)
            }
            composable(Screens.NewDoubt.route) {
                NewDoubt(navController)
            }
        }
    }
}