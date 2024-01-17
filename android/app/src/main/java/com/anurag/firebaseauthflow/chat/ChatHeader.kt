package com.anurag.firebaseauthflow.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun ChatHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(PaddingValues(vertical = 4.dp))
            .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.align(
                Alignment.CenterVertically
            )
        )
        Spacer(modifier = Modifier.width(6.dp))
        Image(
            painter = rememberGlidePainter("https://picsum.photos/100"),
            contentDescription = "Avatar",
            modifier = Modifier.clip(CircleShape),
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "Anurag Shukla",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
        )
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "Back",
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

