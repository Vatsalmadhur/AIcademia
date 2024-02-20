package com.anurag.firebaseauthflow.dashboard.Home

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.math.absoluteValue

@Suppress("UNCHECKED_CAST")
@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun Home(authVM: AuthViewModel, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val authStatus by authVM.currentUser.collectAsState()
    val fs: SkillsViewModel = viewModel()
    val homeVM: homeViewModel = viewModel()
    val gemini: GeminiViewModel = viewModel()
    val db: FSViewModel = viewModel()
    val skills by fs.skills.collectAsState()
    val content by homeVM.content.collectAsState()
    val loading by homeVM.isLoading.collectAsState()
    val pagerState = rememberPagerState { 5 }
    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current
    val quizLoading by gemini.isQuizLoading.collectAsState()
    val contentLoading by gemini.isContentLoading.collectAsState()

    LaunchedEffect(Unit) {
        homeVM.fetchContent()
        val str = FirebaseMessaging.getInstance().token.await()
        db.setFcmKey(str)
    }
    val cards by remember {
        mutableStateOf(listOf(Prompt.INTRO, Prompt.GOALS, Prompt.RES, Prompt.ADVICE, Prompt.QUIZ))
    }

    val btnColor = ButtonDefaults.buttonColors(
        contentColor = MaterialTheme.colorScheme.tertiary,
        containerColor = MaterialTheme.colorScheme.onTertiary
    )
    FlowColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
    ) {
        if (loading || quizLoading || contentLoading)
            LinearProgressIndicator()
        Header(
            title = "Hello ${authStatus.data?.username?.split(" ")?.get(0) ?: ""}",
            desc = "Let's begin today's learning session"
        )
        Divider(thickness = 4.dp, color = MaterialTheme.colorScheme.outline)
        ScrollableTabRow(selectedTabIndex = pagerState.currentPage) {
            cards.forEachIndexed { idx, tab ->
                Tab(
                    selected = pagerState.currentPage == idx,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(idx)
                        }
                    },
                    text = {
                        Text(
                            text = tab.pair.first,
                            maxLines = 1,
                            overflow = TextOverflow.Visible
                        )
                    }
                )
            }
        }
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
                        .fillMaxWidth()
                        .animateContentSize()
                ) {
                    if (content !is Content) {
                        InfoBar(text = "")
                    } else {
                        val prompt = cards[it]
                        when (it) {
                            cards.size - 1 -> {
                                val tabData = content!![prompt] as List<QuizModel>?
                                HomeCard(
                                    title = "Quiz",
                                    modifier = Modifier.graphicsLayer {
                                        val pageOffset = (
                                                (pagerState.currentPage - it) + pagerState
                                                    .currentPageOffsetFraction
                                                ).absoluteValue
                                        alpha = lerp(
                                            start = 0.5f,
                                            stop = 1f,
                                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                        )
                                    },
                                    icon = Icons.Default.Refresh,
                                    iconOnClick = {
                                        if(!quizLoading)
                                        scope.launch {
                                            val res = gemini.enqueueQuiz()
                                            if (res == null) {
                                                Toast.makeText(
                                                    ctx,
                                                    "Error while connecting to server!",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            } else {
                                                Toast.makeText(
                                                    ctx,
                                                    if (res.status) "Refreshing quiz! You'll be notified when its done" else res.message,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        }
                                    }
                                ) {

                                    QuizList(tabData ?: listOf())
                                    Spacer(modifier = Modifier.height(4.dp))
                                    CustomButtonV2(label = "Mark as Complete",
                                        icon = Icons.Default.Done,
                                        onClick = { /*TODO*/ })
                                }
                            }

                            else -> {
                                val tabData = content!![prompt] as String?
                                HomeCard(title = prompt.pair.first) {
                                    RichText {
                                        Markdown(content = tabData ?: "Unable to fetch data")
                                    }
                                    Spacer(modifier = Modifier.height(24.dp))
                                    CustomButtonV2(
                                        label = "Refresh Content",
                                        icon = Icons.Default.Refresh,
                                        buttonColors = btnColor,
                                        isLoading = contentLoading,
                                        onClick = {
                                            scope.launch {
                                                val res = gemini.enqueueContent()
                                                if (res == null) {
                                                    Toast.makeText(
                                                        ctx,
                                                        "Error while connecting to server!",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                } else {
                                                    Toast.makeText(
                                                        ctx,
                                                        if (res.status) "Refreshing content! You'll be notified when its done" else res.message,
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            }
                                        })
                                }
                            }
                        }
                    }

                }

            }
        }
    }
}

