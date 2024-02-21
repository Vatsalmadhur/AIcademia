package com.anurag.firebaseauthflow.doubtDetailsBox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.Header
import com.anurag.firebaseauthflow.common.addComment.AddComment
import com.anurag.firebaseauthflow.doubtForm.DoubtModel
@Composable
fun doubtDetailsBox(doubtModel: DoubtModel) {
    Column {
        Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.outline)
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = doubtModel.title, style = MaterialTheme.typography.titleLarge)
            Divider(Modifier.height(4.dp))
            Text(text = doubtModel.desc, style = MaterialTheme.typography.bodyMedium)
        }
    }

}