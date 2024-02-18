package com.anurag.firebaseauthflow.dashboard.Home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.common.CustomButtonV2
import com.anurag.firebaseauthflow.common.Header
import com.anurag.firebaseauthflow.common.InfoBar
import com.anurag.firebaseauthflow.common.Loader.Loader
import com.anurag.firebaseauthflow.common.Quiz.QuizList
import com.anurag.firebaseauthflow.common.card.HomeCard
import com.anurag.firebaseauthflow.dashboard.Navigation.Screens
import com.anurag.firebaseauthflow.dashboard.profile.SkillsViewModel
import com.anurag.firebaseauthflow.firestore.Content
import com.anurag.firebaseauthflow.firestore.FSViewModel
import com.anurag.firebaseauthflow.firestore.QuizModel
import com.google.firebase.messaging.FirebaseMessaging
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.material3.RichText
import kotlinx.coroutines.tasks.await

@Suppress("UNCHECKED_CAST")
@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun Home(authVM: AuthViewModel, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val authStatus by authVM.currentUser.collectAsState()
    val fs: SkillsViewModel = viewModel()
    val homeVM: homeViewModel = viewModel()
    val db: FSViewModel = viewModel()
    val skills by fs.skills.collectAsState()
    val content by homeVM.content.collectAsState()
    val loading by homeVM.isLoading.collectAsState()
    val pagerState = rememberPagerState { 5 }
    LaunchedEffect(Unit) {
        homeVM.fetchContent()
        val str = FirebaseMessaging.getInstance().token.await()
        db.setFcmKey(str)
    }
    val cards by remember {
        mutableStateOf(listOf(Prompt.INTRO, Prompt.GOALS, Prompt.RES, Prompt.ADVICE, Prompt.QUIZ))
    }
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
            CustomButtonV2(label = "Choose skill",
                icon = Icons.Default.Edit,
                onClick = { navController.navigate(Screens.Profile.route) })
        } else if (loading) {
            Loader()
            InfoBar(text = "Please wait...")
        } else {
            if (content == null || content?.equals(null) == true) {
                InfoBar(text = "Unable to load content at the moment")
                CustomButtonV2(label = "Reload",
                    icon = Icons.Default.Refresh,
                    onClick = { homeVM.fetchContent() })
            } else {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (content !is Content ) {
                        InfoBar(text = "")
                    } else {
                        val prompt = cards[it]
                        when(it) {
                            cards.size - 1 -> {
                                val tabData = content!![prompt] as List<QuizModel>?
                                HomeCard(title = "Mark as Complete", onClick = { /*TODO*/ }) {
                                    QuizList(tabData  ?: listOf())
                                    Spacer(modifier = Modifier.height(4.dp))
                                    CustomButtonV2(label = "Mark as Complete",
                                        icon = Icons.Default.Done,
                                        onClick = { /*TODO*/ })
                                }
                            }
                            else -> {
                                val tabData = content!![prompt] as String?
                                HomeCard(title = prompt.pair.first, onClick = { /*TODO*/ }) {
                                    RichText {
                                        Markdown(content = tabData ?: "Unable to fetch data")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}