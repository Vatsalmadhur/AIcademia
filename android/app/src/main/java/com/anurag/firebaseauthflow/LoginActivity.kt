package com.anurag.firebaseauthflow

import FirebaseAuthFlowTheme
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.anurag.firebaseauthflow.auth.GoogleAuthUiClient
import com.anurag.firebaseauthflow.auth.ui.LoginScreen
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    private lateinit var gAuth: GoogleAuthUiClient
    private lateinit var ctx: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        ctx = this
        gAuth = GoogleAuthUiClient(ctx, Identity.getSignInClient(this))

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                lifecycleScope.launch {
                    val signInRes = gAuth.signInWithIntent(result.data ?: return@launch)
                    if (signInRes.status) {
                        Toast.makeText(
                            ctx, "Welcome back ${signInRes.data?.username}", Toast.LENGTH_SHORT
                        ).show()
                        ctx.startActivity(Intent(ctx, MainActivity::class.java))
                    } else {
                        Toast.makeText(ctx, "${signInRes.errormessage}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                println("error")
            }
        }
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseAuthFlowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    LoginScreen(onSignIn = {
//                        lifecycleScope.launch {
//                            val intentSender = gAuth.signin()
//                            launcher.launch(
//                                IntentSenderRequest.Builder(intentSender ?: return@launch).build()
//                            )
//                        }
//                    }
//                    )
                }
            }
        }
    }
}
