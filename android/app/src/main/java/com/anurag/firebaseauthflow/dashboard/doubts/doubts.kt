package com.anurag.firebaseauthflow.dashboard.doubts


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.InfoBar
import com.anurag.firebaseauthflow.doubtCard.doubtCard
import com.anurag.firebaseauthflow.doubtForm.Form
import kotlin.math.round

@Composable
@Preview
fun Doubts() {
    var scrollState= rememberScrollState()
    Box (modifier = Modifier.fillMaxSize()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        doubtCard()
        doubtCard()
        doubtCard()
        doubtCard()
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