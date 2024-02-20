package com.anurag.firebaseauthflow.dashboard.doubts


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.anurag.firebaseauthflow.common.CustomButton
import com.anurag.firebaseauthflow.common.Header
import com.anurag.firebaseauthflow.common.InfoBar
import com.anurag.firebaseauthflow.dashboard.Navigation.Screens
import com.anurag.firebaseauthflow.doubtCard.DoubtCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Doubts(navController: NavHostController) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val postsVM: DoubtsListViewModel = viewModel()
    LaunchedEffect(Unit) {
        postsVM.getPosts()
    }

    val pagerState = rememberPagerState {
        2
    }
    val scrollCoroutineScope = rememberCoroutineScope()

    val cards by remember {
        mutableStateOf(listOf("Helpdesk", "Answerable"))
    }
    val posts by postsVM.posts.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Header(
                title = "Community",
                desc = "Interact with community to resolve your doubts and help others if you can",
            )

            Divider(thickness = 4.dp, color = MaterialTheme.colorScheme.outline)
            TabRow(selectedTabIndex = pagerState.currentPage) {
                cards.forEachIndexed { idx, tab ->
                    Tab(
                        selected = pagerState.currentPage == idx,
                        onClick = {
                            scrollCoroutineScope.launch {
                                pagerState.animateScrollToPage(idx)
                            }
                        },
                        text = {
                            Text(
                                text = tab,
                                maxLines = 1,
                                overflow = TextOverflow.Visible
                            )
                        }
                    )
                }
            }
            if (posts.isEmpty()) {
                InfoBar(text = "No posts Available")
            } else
                posts.forEach {
                    DoubtCard(it, navController)
                }
            CustomButton(label = "Load more") {
                scope.launch {
                    postsVM.getPosts()
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(32.dp, 16.dp)
                .align(Alignment.BottomEnd)
        ) {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.NewDoubt.route) },
                containerColor = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.extraLarge,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = "New Doubt",
                    modifier = Modifier.size(30.dp),
                )
            }
        }
    }


}