package com.anurag.firebaseauthflow.dashboard.Navigation

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Doubts : Screens("doubts")
    object Profile : Screens("profile")
    object PriorSkills : Screens("prior_skill")
    object NewSkill : Screens("new_skill")

    object NewDoubt: Screens("new-doubt")
}