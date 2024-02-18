package com.anurag.firebaseauthflow.doubtCard


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.ui.Modifier
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anurag.firebaseauthflow.common.Header


@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview
fun doubtCard() {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
        modifier = Modifier
            .width(350.dp)
            .padding(10.dp)
    ) {
        Column {
            Column(Modifier.padding(16.dp,16.dp)) {
                Text(
                    text = "Doubt",
//                    modifier = Modifier.
//                        .padding(16.dp,16.dp,16.dp,3.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Left,
                    fontSize = 25.sp
                )
                Box(modifier = Modifier
                    .width(30.dp)
                    .background(MaterialTheme.colorScheme.onBackground)
                    .height(2.dp))

            }


            Text(
                text = "Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet,",
                modifier = Modifier
                    .padding(16.dp,1.dp,0.dp,10.dp),
                fontSize = 15.sp,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Left,
                )

            Divider( thickness = 1.dp, color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 2.dp)
            )

            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 2.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                Row {


                    Box(modifier = Modifier
                        .padding(16.dp, 2.dp)
                        .height(30.dp)
                        .border(3.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(8.dp))
                        ) {

                        SuggestionChip(
                            onClick = {  },
                            label = {
                                Text(text = "#Kotlin", color = MaterialTheme.colorScheme.onError)
                            },
                            Modifier
                                .background(
                                    MaterialTheme.colorScheme.error,
                                    RoundedCornerShape(8.dp)
                                )
                                .fillMaxHeight()



                        )
                    }
                }
                Row {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Sharp.FavoriteBorder, contentDescription = "Localized description")
                    }


                }


            }




        }
    }
}