package com.anurag.firebaseauthflow.common

import FirebaseAuthFlowTheme
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Header(title: String, desc:String = "") {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.height(2.dp))
            if (!desc.isBlank())
            Text(
                text = desc,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Light,
            )else
                Box(modifier = Modifier
                    .width(100.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .height(2.dp))
        }
    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun HeaderPreview() {
    FirebaseAuthFlowTheme {
        Surface (modifier = Modifier.fillMaxSize()){
            Column {
                Header(title = "Skills you know")
                Header(title = "Skills you don't")
            }
        }
    }
}