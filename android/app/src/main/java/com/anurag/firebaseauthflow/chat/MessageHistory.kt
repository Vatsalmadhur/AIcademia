@file:JvmName("MessageHistoryKt")

package com.anurag.firebaseauthflow.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MessageHistory() {
    var msgs by remember {
        mutableStateOf(
            listOf(
                Message(_msg = "Hello, How are you my friend ? ", _self = true),
                Message(_msg = "Heyyy, I am alright. What's up ?", _self = false),
                Message(
                    _self = true,
                    _msg = "Nothing much. Just wanted to know the reason behind your existence."
                )
            )
        )
    }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(msgs) { item ->
            ChatItem(message = item)
        }
    }
}


@Composable
fun ChatItem(message: Message) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .align(if (message.self) Alignment.End else Alignment.Start)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (message.self) 48f else 0f,
                        bottomEnd = if (message.self) 0f else 48f
                    )
                )
                .background(Color.Gray)
                .padding(16.dp)
        ) {
            Text(text = message.msg)
        }
    }
}
