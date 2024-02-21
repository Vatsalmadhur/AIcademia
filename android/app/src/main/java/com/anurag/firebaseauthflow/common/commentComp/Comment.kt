package com.anurag.firebaseauthflow.common.commentComp

import FirebaseAuthFlowTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.R
import com.anurag.firebaseauthflow.doubtForm.CommentModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Comment(comment: CommentModel) {
    FlowColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp))
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(100))
                        .background(MaterialTheme.colorScheme.primary),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Avatar",
                    )
                }
                Text(
                    text = "@${comment.uid?.substring(0,8)}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "2 days ago",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold
            )
        }
        Divider(thickness = 1.dp)
        Text(
            text = comment.body,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Box(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.tertiary,
                RoundedCornerShape(16.dp)
            ),
        )
        Row(
            Modifier
                .clip(RoundedCornerShape(100))
                .background(MaterialTheme.colorScheme.primary)
                .padding(2.dp, 0.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Upvote",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Text(text = comment.votes.toString(), color = MaterialTheme.colorScheme.onPrimary)
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down vote",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

    }


}

@Preview
@Composable
fun CommentPreview() {
    FirebaseAuthFlowTheme {
        Comment(
            comment = CommentModel(
                body = "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared wi",
                uid = "anurag",
                votes = 5
            )
        )
    }

}