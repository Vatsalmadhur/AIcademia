package com.anurag.firebaseauthflow.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignupViewModel: ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    fun setEmail(str: String) {
        _email.value = str
    }

    fun setPassword(str: String) {
        _password.value = str
    }

    fun setName(str: String) {
        _name.value = str
    }
}