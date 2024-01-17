package com.anurag.firebaseauthflow.common.AppBar

import FirebaseAuthFlowTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anurag.firebaseauthflow.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scope = rememberCoroutineScope()
    val isDarkMode = isSystemInDarkTheme()
    TopAppBar(actions = {
        IconButton(onClick = {}) {}
    }, navigationIcon = {
        IconButton(onClick = {
            scope.launch {
                drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        }) {

            Icon(
                imageVector = if (drawerState.isClosed) Icons.Filled.Menu else Icons.Default.Close,
                contentDescription = "Localized description"
            )
        }
    }, scrollBehavior = scrollBehavior, title = {
        Image(
            painterResource(id = if (isDarkMode) R.drawable.logo_white else R.drawable.logo_black),
            contentDescription = "Logo",
            modifier = Modifier.width(150.dp),
        )
    })
}
