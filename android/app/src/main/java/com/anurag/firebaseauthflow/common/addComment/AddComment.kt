package com.anurag.firebaseauthflow.common.addComment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddComment(onSubmit:(str:String)->Unit) {
    val str = remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = str.value,
            maxLines = 5,
            onValueChange = {
                str.value = it
            }, placeholder = { Text(text = "Answer") },
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.tertiary)
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        )

        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxHeight()) {
            IconButton(
                onClick = {
                    onSubmit(str.value)
                          str.value = ""
                          } , modifier = Modifier
                    .size(50.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(100)
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        }
    }
}