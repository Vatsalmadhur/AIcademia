package com.anurag.firebaseauthflow.doubtForm

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.anurag.firebaseauthflow.SkillChooser.SkillChooser
import com.anurag.firebaseauthflow.common.CustomButtonV2
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun NewDoubt(navController: NavHostController) {
    val doubtVM: DoubtViewModel = viewModel()
    val title by doubtVM.title.collectAsState()
    val desc by doubtVM.desc.collectAsState()
    val loading by doubtVM.loading.collectAsState()
    val searchVM = doubtVM.searchVM
    val ctx = LocalContext.current
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val fieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline
    )

    FlowColumn(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "Ask the Community",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp),

            )
        OutlinedTextField(
            value = title,
            onValueChange = { doubtVM.setTitle(it) },
            label = { Text("Heading (Keep it short and sweet!)") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(bottom = 16.dp),
            colors = fieldColors
        )
        OutlinedTextField(
            value = desc,
            onValueChange = { doubtVM.setDesc(it) },
            label = { Text("What do you wanna ask?") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(200.dp),
            colors = fieldColors
        )

        SkillChooser(searchVM = searchVM, useV1 = true, skills = listOf())
        CustomButtonV2(
            label = "POST",
            isLoading = loading,
            icon = Icons.Default.AddCircle,
            onClick = {
                scope.launch {
                    val res = doubtVM.PostDoubt()
                    if (res) {
                        navController.popBackStack()
                        Toast.makeText(ctx, "Doubt posted successfully!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(
                            ctx,
                            "Error posting doubt! Please try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        )
    }
}
