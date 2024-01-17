package com.anurag.firebaseauthflow.auth

data class SignedInState(
    val isSignInSuccess:Boolean = false,
    val signInErrorMsg : String? = null
)