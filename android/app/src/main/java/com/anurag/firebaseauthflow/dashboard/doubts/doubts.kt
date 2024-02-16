package com.anurag.firebaseauthflow.dashboard.doubts

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.InfoBar
import com.anurag.firebaseauthflow.doubtCard.doubtCard

@Composable
@Preview
fun Doubts() {
    var scrollState= rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
//            .border(2.dp, MaterialTheme.colorScheme.error),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
//        InfoBar(text = "Coming Soooon")
        doubtCard()
        doubtCard()
        doubtCard()
        doubtCard()
//        doubtCard()
//        doubtCard()
//        doubtCard()

    }

}