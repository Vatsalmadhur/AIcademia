package com.anurag.firebaseauthflow.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.ai.client.generativeai.GenerativeModel

@Composable
fun ChatMain(model: GenerativeModel) {
    Column(
    modifier = Modifier
    .fillMaxSize()
    .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .height(50.dp)
        ) {
            ChatHeader()
        }
        Column(modifier = Modifier.weight(1f)) {
            MessageHistory()
        }
        Row(
            modifier = Modifier
                .height(50.dp)
        ) {
            MessageBox(modifier = Modifier, model = model)
        }
    }
}