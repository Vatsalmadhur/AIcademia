package com.anurag.firebaseauthflow.firestore

data class About(
    val acquired_skills:List<String>? = listOf(),
    val current_skill:String? = null
)