package com.anurag.firebaseauthflow.doubtDetailsBox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.Header
import com.anurag.firebaseauthflow.common.commentComp.commentComponent
import com.anurag.firebaseauthflow.doubtForm.DoubtModel

@Composable
fun doubtDetailsBox(doubtModel: DoubtModel){
    Column (verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.Start, modifier = Modifier.padding(8.dp)){
         Text(text = doubtModel.title, style = MaterialTheme.typography.titleLarge)
        Text(text = doubtModel.desc, style = MaterialTheme.typography.bodyMedium)


    }
    Header(title = "Comments")

}