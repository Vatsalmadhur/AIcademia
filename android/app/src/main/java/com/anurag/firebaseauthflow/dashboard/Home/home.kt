package com.anurag.firebaseauthflow.dashboard.Home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.common.CustomButtonV2
import com.anurag.firebaseauthflow.common.Header
import com.anurag.firebaseauthflow.common.InfoBar
import com.anurag.firebaseauthflow.common.card.HomeCard
import com.anurag.firebaseauthflow.dashboard.profile.SkillsViewModel
import com.anurag.firebaseauthflow.gemini.Gemini

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Home(authVM: AuthViewModel, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val authStatus by authVM.currentUser.collectAsState()
    val fs = remember {
        SkillsViewModel()
    }
    val skills by fs.skills.collectAsState()
    val homeVM = remember {
        homeViewModel()
    }
    val gemini = remember {
        Gemini()
    }
    gemini.setDataVM(homeVM, skills)
    val intro by homeVM.intro.collectAsState()
    val goals by homeVM.goals.collectAsState()
    val advice by homeVM.advice.collectAsState()
    val res by homeVM.resources.collectAsState()

    val cards = listOf(
        Prompt.INTRO, Prompt.GOALS, Prompt.ADVICE, Prompt.RES
    )

    FlowColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
    ) {

        Header(title = "Hello ${authStatus.data?.username?.split(" ")?.get(0) ?: ""}")
        if (skills.current_skill.isNullOrBlank()) {
            InfoBar(text = "Please Choose your skills to proceed")
        } else {
            HomeCard(title = "Introduction", onClick = { /*TODO*/ }) {
                if (intro.isBlank()) {
                    Text("Loading...")
                } else {
                    intro.split(";;;").forEach {
                        Text(text = it.trim(), modifier = Modifier)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            HomeCard(title = "Today's goals", onClick = { /*TODO*/ }) {
                if (goals.isBlank()) {
                    Text("Loading...")
                } else {
                    goals.split(";;;").forEach {
                        Text(text = it.trim(), modifier = Modifier)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            HomeCard(title = "Expert's Advice", onClick = { /*TODO*/ }) {
                if (advice.isBlank()) {
                    Text("Loading...")
                } else {
                    advice.split(";;;").forEach {
                        Text(text = it.trim(), modifier = Modifier)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            HomeCard(title = "Resources", onClick = { /*TODO*/ }) {
                if (res.isBlank()) {
                    Text("Loading...")
                } else {
                    res.split(";;;").forEach {
                        Text(text = it.trim(), modifier = Modifier)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
//            content.forEachIndexed { idx, it ->
//                HomeCard(title = cards[idx].pair.first, onClick = { /*TODO*/ }) {
//                    if (it.isNullOrBlank()) {
//                        Text("Loading...")
//                    } else {
//                        it.split(";;;").forEach {
//                            Text(text = it.trim(), modifier = Modifier)
//                            Spacer(modifier = Modifier.height(8.dp))
//                        }
//                    }
//                }
//            }

        }
        HomeCard(title = "Mark as Complete", onClick = { /*TODO*/ }) {
            CustomButtonV2(label = "Mark as Complete",
                icon = Icons.Default.Done,
                onClick = { /*TODO*/ })
        }
    }
}