package com.anurag.firebaseauthflow.dashboard.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.common.CustomButtonV2
import com.anurag.firebaseauthflow.common.InfoBar
import com.anurag.firebaseauthflow.common.SkillCard
import com.anurag.firebaseauthflow.common.card.HomeCardClickable
import com.anurag.firebaseauthflow.common.userForm.userForm
import com.anurag.firebaseauthflow.dashboard.Navigation.Screens
import com.anurag.firebaseloginflow.presentation.sign_in.UserData
import com.google.accompanist.glide.rememberGlidePainter

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Profile(authVM: AuthViewModel, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val authStatus by authVM.currentUser.collectAsState()
    val logoutIcon: ImageVector = Icons.TwoTone.ExitToApp
    val logoutBtnColors = ButtonDefaults.buttonColors(
        contentColor = MaterialTheme.colorScheme.onError,
        containerColor = MaterialTheme.colorScheme.error
    )
    val fs = remember{
        SkillsViewModel()
    }
    val skills by fs.skills.collectAsState()
    FlowColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
    ) {
        (if (authStatus.status) authStatus.data else {
            authVM.logout()
            null
        })?.let {
            UserProfile(
                it
            )
        }

        HomeCardClickable(title = "Acquired Skills", desc = "Choose the skills you are familiar with", onClick = {
            navController.navigate(Screens.PriorSkills.route)
        }) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            ) {
                if ((skills.acquired_skills?.size ?: 0) > 0)
                    skills.acquired_skills?.forEach {
                        SkillCard(skill = it, onClick = {}, selected = true, disabled = true)
                    } else
                    InfoBar(text = "Click to add",)
            }
        }

        HomeCardClickable(title = "Currently Learning", desc = "Choose the skill you want to learn", onClick = {
            navController.navigate(Screens.NewSkill.route)
        }) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            ) {
                listOf(skills.current_skill).forEach {
                    if (it != null) {
                        SkillCard(skill = it, onClick = {}, selected = true, disabled = true)
                    } else
                        InfoBar(text = "Click to add")
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            CustomButtonV2(
                label = "Logout",
                icon = logoutIcon,
                onClick = { authVM.logout() },
                buttonColors = logoutBtnColors
            )
        }
    }
}

@Composable
fun UserProfile(user: UserData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberGlidePainter(
                request = user.profilePictureUrl
                    ?: "https://ui-avatars.com/api/?name=${
                        user.username?.split(" ")?.joinToString("+")
                    }"
            ),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = user.username ?: "Username unavailable",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.MailOutline, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = user.email ?: "Email unavailable")
        }
    }
}

