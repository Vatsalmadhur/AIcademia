package com.anurag.firebaseauthflow.common.GoogleLogin

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.R
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@Composable
fun GoogleLogin(authVM:AuthViewModel) {
    val vm = GoogleViewModel()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                coroutineScope.launch {
                    val userData = vm.signWithCredential(credential)
                    authVM.setSignnInResult(userData)
                    if (userData.status) {
                        Toast.makeText(
                            context,
                            userData.errormessage ?: "Signin successful",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(context, userData.errormessage ?: "error", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } catch (e: ApiException) {
                e.printStackTrace()
                Toast.makeText(context, e.message ?: "error", Toast.LENGTH_LONG).show()
            }
        }

    val isDarkMode = isSystemInDarkTheme()

    TextButton(
        modifier = Modifier
            .border(
                1.dp,
                MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth(),
        onClick = {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.WEB_CLIENT_ID))
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(context, gso)
            launcher.launch(googleSignInClient.signInIntent)
        }) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(if (isDarkMode) R.drawable.google else R.drawable.google_black),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            Text(text = "Login with google")
        }
    }
}


@Composable
fun OR() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "OR", fontWeight = FontWeight.Bold)
    }
}