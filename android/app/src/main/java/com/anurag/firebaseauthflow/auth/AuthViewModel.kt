package com.anurag.firebaseauthflow.auth

import androidx.lifecycle.ViewModel
import com.anurag.firebaseloginflow.presentation.sign_in.SignInResult
import com.anurag.firebaseloginflow.presentation.sign_in.UserData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val auth = Firebase.auth
    private val _currentUser: MutableStateFlow<SignInResult> =
        MutableStateFlow(SignInResult(false, null, null))
    val currentUser = _currentUser.asStateFlow()

    fun setSignnInResult(res: SignInResult){
        _currentUser.value = res
    }

    fun logout(){
        auth.signOut()
        resetAuth()
    }

    fun resetAuth(){
        _currentUser.value = SignInResult(false, null, null)
    }

    fun getCurrentUser(): SignInResult? {
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            val user = UserData(
                userId = firebaseUser.uid,
                username =  firebaseUser.displayName,
                email = firebaseUser.email,
                profilePictureUrl = firebaseUser.photoUrl.toString()
            )
            _currentUser.value = SignInResult(true, user, null)
            return _currentUser.value
        } else {
            _currentUser.value = SignInResult(false, null, null)
        }
        return null
    }
}