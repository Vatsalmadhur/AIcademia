package com.anurag.firebaseauthflow.auth.ui

import FirebaseAuthFlowTheme
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.Navigation.Navigation_main
import com.anurag.firebaseauthflow.R
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.auth.LoginViewModel
import com.anurag.firebaseauthflow.auth.SignupViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AuthScreen(authVM: AuthViewModel) {
    val isDarkMode = isSystemInDarkTheme()
    val isLogin = remember {
        mutableStateOf(true)
    }
    val loginVM = LoginViewModel()
    val signupVM = SignupViewModel()
    FirebaseAuthFlowTheme {
        FlowColumn(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(Modifier.height(50.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Image(
                    painterResource(id = if (isDarkMode) R.drawable.logo_white else R.drawable.logo_black),
                    contentDescription = "Logo",
                    modifier = Modifier.widthIn(100.dp, 200.dp),
                )
            }
            Spacer(Modifier.height(42.dp))
            if (isLogin.value) LoginScreen(isLogin, loginVM, authVM) {} else SignupScreen(isLogin, signupVM, authVM) {}
        }

    }
}
