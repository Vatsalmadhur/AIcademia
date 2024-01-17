package com.anurag.firebaseloginflow.presentation.sign_in

data class SignInResult(
    val status: Boolean,
    val data: UserData?,
    val errormessage: String?
)

data class UserData(
    val userId: String,
    val email: String?,
    val username: String?,
    val profilePictureUrl: String?
)