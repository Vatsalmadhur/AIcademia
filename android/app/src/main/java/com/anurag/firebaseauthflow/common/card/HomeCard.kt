package com.anurag.firebaseauthflow.common.card

import FirebaseAuthFlowTheme
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anurag.firebaseauthflow.R
import com.anurag.firebaseauthflow.common.Header
import com.google.accompanist.glide.rememberGlidePainter
import okhttp3.internal.wait

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCardClickable(title: String, onClick: () -> Unit, content: @Composable() () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Header(title = title)
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}


@Composable
fun HomeCard(modifier: Modifier = Modifier, title: String, content: @Composable() () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header(title = title)
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}


@Composable
fun IntroCard() {
    Card(
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp) , modifier = Modifier.height(50.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentScale = ContentScale.Fit
                )
                Text(text = "Anurag Shukla", style = TextStyle(fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground))
            }

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(50.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
@Preview()
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
fun CardPreview() {
    FirebaseAuthFlowTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            IntroCard()
        }
    }
}