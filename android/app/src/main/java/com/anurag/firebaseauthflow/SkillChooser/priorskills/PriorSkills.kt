package com.anurag.firebaseauthflow.SkillChooser.priorskills

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anurag.firebaseauthflow.SkillChooser.SkillChooser
import com.anurag.firebaseauthflow.common.Header
import com.anurag.firebaseauthflow.common.Searchbar.SearchViewModel
import com.anurag.firebaseauthflow.dashboard.Navigation.Screens
import com.anurag.firebaseauthflow.dashboard.profile.SkillsViewModel
import com.anurag.firebaseauthflow.firestore.FSViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PriorSkills(navController: NavHostController) {
    val scrollState = rememberScrollState()
    val searchVM = remember {
        SearchViewModel()
    }
    val fs = remember{
        FSViewModel()
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val skillsVM = remember{
        SkillsViewModel()
    }
    val skills by skillsVM.skills.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        FlowColumn(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .verticalScroll(scrollState),
        ) {
            Header(title = "Acquired skills")
            SkillChooser(searchVM, skills.acquired_skills)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        scope.launch {
                            val res = fs.updateAcquiredSkills(searchVM.selected.value.toList())
                            if (res) {
                                Toast.makeText(
                                    context,
                                    "Skills updated successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(Screens.Profile.route)
                            } else {
                                Toast.makeText(context, "Error updating skills", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    },
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text(
                        text = "Save",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}