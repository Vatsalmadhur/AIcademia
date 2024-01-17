package com.anurag.firebaseauthflow

import FirebaseAuthFlowTheme
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.anurag.firebaseauthflow.Navigation.Navigation_main
import com.anurag.firebaseauthflow.SkillChooser.SkillChooser
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.auth.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var gAuth: GoogleAuthUiClient
    private lateinit var authVM:AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gAuth = GoogleAuthUiClient(this, Identity.getSignInClient(this))
        authVM = AuthViewModel()
        authVM.getCurrentUser()
//        val model = GenerativeModel(
//            modelName = "gemini-pro",
//            apiKey = "AIzaSyDnjDtw6KaQ_Gn6Y42VMlzE2QQpgeQvoFY"
//        )
        setContent {
            FirebaseAuthFlowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Navigation_main(authVM)
                }
            }
        }
    }
}


@Composable
@Preview(name = "LightMode", uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun MainActivityPreview() {
    FirebaseAuthFlowTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Navigation_main()
        }
    }
}

