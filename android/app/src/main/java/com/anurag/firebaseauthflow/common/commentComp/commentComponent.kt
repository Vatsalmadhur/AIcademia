package com.anurag.firebaseauthflow.common.commentComp

import FirebaseAuthFlowTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.common.Header
import com.anurag.firebaseauthflow.doubtForm.CommentModel
import com.anurag.firebaseauthflow.doubtForm.DoubtModel
@Composable
fun commentComponent(comment:CommentModel){
            OutlinedCard(modifier = Modifier
                .padding(8.dp)
                .border(1.dp, color = MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                .fillMaxWidth()){
                Column(Modifier.padding(8.dp)) {
                    Text(text = "@"+comment.uid.toString(), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.outline)
                    Text(text = comment.body,style = MaterialTheme.typography.titleMedium)
                    Row (Modifier.width(80.dp).padding(top = 4.dp, bottom = 4.dp), horizontalArrangement = Arrangement.SpaceBetween){
                        Text(text = "Likes:" + comment.votes, style = MaterialTheme.typography.bodyLarge)
                        IconButton(onClick = { /* doSomething() */ }, modifier = Modifier.size(20.dp)) {
                            Icon(Icons.Outlined.ThumbUp, contentDescription = "Localized description")
                        }
                    }
                }
                
            }

            
        }

@Preview
@Composable
fun commentPreview(){
    FirebaseAuthFlowTheme {
        Surface {
            commentComponent(comment = CommentModel())
        }
    }

}