package com.anurag.firebaseauthflow.auth.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.auth.SignupViewModel
import com.anurag.firebaseauthflow.common.CustomButton
import com.anurag.firebaseauthflow.common.GoogleLogin.GoogleLogin
import com.anurag.firebaseauthflow.common.Header
import com.anurag.firebaseauthflow.common.InputField
import com.anurag.firebaseauthflow.common.GoogleLogin.OR


@Composable
fun SignupScreen(isLogin: MutableState<Boolean>, signupVM: SignupViewModel , authVM:AuthViewModel, onSignIn: () -> Unit) {
    val email by signupVM.email.collectAsState()
    val password by signupVM.password.collectAsState()
    val name by signupVM.name.collectAsState()
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(title = "Welcome,", desc = "Sign up to continue")
        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier.widthIn(max = 400.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
        ) {
            GoogleLogin(authVM)
            OR()
            InputField(label = "Name", value = name, onValueChange = {signupVM.setName(it)}, type = KeyboardType.Text)
            InputField(label = "Email", value = email, onValueChange = {signupVM.setEmail(it)}, type = KeyboardType.Email)
            InputField(
                label = "Password",
                value = password,
                onValueChange = {signupVM.setPassword(it)},
                type = KeyboardType.Password,
                isPassword = true
            )
            CustomButton(label = "Signup") {}
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Already have an account?")
                Text(text = "Login", textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable(true) {
                        isLogin.value = true
                    })
            }
        }
    }

}
