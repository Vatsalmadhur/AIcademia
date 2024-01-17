package com.anurag.firebaseauthflow.auth.ui

import FirebaseAuthFlowTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.Navigation.Navigation_main
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.auth.LoginViewModel
import com.anurag.firebaseauthflow.common.CustomButton
import com.anurag.firebaseauthflow.common.GoogleLogin.GoogleLogin
import com.anurag.firebaseauthflow.common.Header
import com.anurag.firebaseauthflow.common.InputField
import com.anurag.firebaseauthflow.common.GoogleLogin.OR


@Composable
fun LoginScreen(isLogin: MutableState<Boolean>, loginVM: LoginViewModel,authVM:AuthViewModel, onSignIn: () -> Unit) {

    val email by loginVM.email.collectAsState()
    val password by loginVM.password.collectAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(title = "Welcome back,", desc = "Sign in to continue")
        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .widthIn(max = 400.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
        ) {
            GoogleLogin(authVM)
            OR()
            InputField(
                label = "Email",
                value = email,
                onValueChange = { loginVM.setEmail(it) },
                type = KeyboardType.Email)
            InputField(
                label = "Password",
                value = password,
                onValueChange = { loginVM.setPassword(it) },
                type = KeyboardType.Password,
                isPassword = true)
            CustomButton(label = "Login") {}
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "First time here?")
                Text(text = "Signup", textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable(true) {
                        isLogin.value = false
                    })
            }
        }
    }
}

@Composable
@Preview()
fun LoginScreenPreview(){
    FirebaseAuthFlowTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Navigation_main()
        }
    }
}
