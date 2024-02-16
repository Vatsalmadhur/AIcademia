package com.anurag.firebaseauthflow.Doubts

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.ui.Modifier
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun OutlinedCardExample() {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.error),
        modifier = Modifier
            .size(width = 240.dp, height = 150.dp)
    ) {
        Column {
            Text(
                text = "Doubt",
                modifier = Modifier
                    .padding(16.dp,16.dp,16.dp,3.dp),
                textAlign = TextAlign.Left,
                fontSize = 18.sp
            )
            Text(
                text = "Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,",
                modifier = Modifier
                    .padding(16.dp,1.dp),
                fontSize = 10.sp,
                textAlign = TextAlign.Left,

            )
            Divider( thickness = 1.dp, color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 2.dp)
            )


            Box(modifier = Modifier.padding(16.dp,2.dp).height(20.dp)) {
                AssistChip(
                    onClick = { Log.d("Assist chip", "hello world") },
                    label = { Text("#kotlin", fontSize = 10.sp, color = MaterialTheme.colorScheme.onError) },
                    Modifier
                        .background(MaterialTheme.colorScheme.error, RoundedCornerShape(8.dp))
                        .fillMaxHeight()




                )
            }



        }
    }
}