package com.anurag.firebaseauthflow.dashboard.doubts


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.doubtCard.DoubtCard

@Composable
@Preview
fun Doubts() {
    var scrollState= rememberScrollState()
    Box (modifier = Modifier.fillMaxSize()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DoubtCard()
        DoubtCard()
        DoubtCard()
        DoubtCard()
    }
        Column (
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            FloatingActionButton(
                onClick = { },
//                modifier = Modifier.align(Alignment.CenterHorizontally),
                containerColor = MaterialTheme.colorScheme.error,
                shape = MaterialTheme.shapes.extraLarge

                ) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = "New Doubt",
                    modifier = Modifier.size(30.dp),
                    MaterialTheme.colorScheme.onSecondary,


                )
            }
        }
    }


}