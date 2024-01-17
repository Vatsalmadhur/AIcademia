package com.anurag.firebaseauthflow.auth.model

class LoginModel {
    var email = ""
    val password = ""
}

data class SignupModel(
    val email: String,
    val password: String,
    val name: String
)