package com.anurag.firebaseauthflow.common.addComment

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.CustomButton

@Composable
fun addComment(){

Row( horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
    
    TextField(
        value = "",
        onValueChange = {
        }, placeholder = { Text(text = "Add yours") },
        modifier = Modifier
            .padding(8.dp)
//            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(2.dp))
            .fillMaxHeight()
            )
    CustomButton(label = "Add", onClick = {})




}}