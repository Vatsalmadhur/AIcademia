package com.anurag.firebaseauthflow.doubtCard


import FirebaseAuthFlowTheme
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anurag.firebaseauthflow.dashboard.Navigation.Screens
import com.anurag.firebaseauthflow.doubtForm.DoubtModel


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DoubtCard(doubtModel:DoubtModel, navController: NavController) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        onClick = {
            navController.navigate(Screens.DoubtDetails.route.plus("/${doubtModel.postId}"))
        }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = doubtModel.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = doubtModel.desc,
                fontSize = 16.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                doubtModel.tags.forEach {
                    DoubtTag(label = it)
                }
            }
        }
    }
}

@Composable
fun DoubtTag(label: String) {
    Box(modifier = Modifier.clip(RoundedCornerShape(200.dp))){
    Text(
        text = "#$label",
        modifier = Modifier
            .background(MaterialTheme.colorScheme.outline)
            .padding(4.dp, 1.dp),
        style = TextStyle(color = MaterialTheme.colorScheme.onPrimary),
        fontWeight = FontWeight.Bold
    )}
}