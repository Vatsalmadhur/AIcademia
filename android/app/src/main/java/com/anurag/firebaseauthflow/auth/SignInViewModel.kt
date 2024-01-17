package com.anurag.firebaseauthflow.auth

import androidx.lifecycle.ViewModel
import com.anurag.firebaseloginflow.presentation.sign_in.SignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignedInState())
    val state = _state.asStateFlow()

    fun onSignInReult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccess = result.data != null,
                signInErrorMsg = result.errormessage
            )
        }
    }
    fun resetState(){
        _state.update { SignedInState() }
    }
}