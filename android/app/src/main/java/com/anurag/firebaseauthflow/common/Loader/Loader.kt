package com.anurag.firebaseauthflow.common.Loader

import FirebaseAuthFlowTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Loader(){
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
    )
}
@Preview()
@Composable
fun LoaderPreview(){
    FirebaseAuthFlowTheme {
      Surface (modifier = Modifier.fillMaxSize()){
          Loader()
      }
    }
}