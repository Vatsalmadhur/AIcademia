package com.anurag.firebaseauthflow.doubtCard


import FirebaseAuthFlowTheme
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DoubtCard() {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "How to center a div ?? I am a noob, please help me.",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,",
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("react", "javascript", "web development").forEach {
                    DoubtTag(label = it)
                }
            }
        }
    }
}

@Composable
fun DoubtTag(label: String) {
    Text(
        text = "#$label",
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline,
                RoundedCornerShape(4.dp)
            )
            .padding(4.dp),
        style = TextStyle(color = MaterialTheme.colorScheme.onBackground)
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Preview()
@Composable
fun DoubtCardPreview() {
    FirebaseAuthFlowTheme {
        Surface {
            DoubtCard()
        }
    }
}