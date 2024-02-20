package com.anurag.firebaseauthflow.dashboard.doubts

import FirebaseAuthFlowTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.anurag.firebaseauthflow.common.CustomButton
import com.anurag.firebaseauthflow.common.InfoBar
import com.anurag.firebaseauthflow.common.commentComp.commentComponent
import com.anurag.firebaseauthflow.doubtCard.DoubtCard
import com.anurag.firebaseauthflow.doubtDetailsBox.doubtDetailsBox
import kotlinx.coroutines.launch

@Composable
fun DoubtDetails(doubtId: String?, navController: NavHostController) {

    val scope = rememberCoroutineScope()
    val detailsVM:DoubtDetailsViewModel = viewModel()
    val isLoadingPost by detailsVM.isLoadingPost.collectAsState()
    val isLoadingComments by detailsVM.isLoadingComments.collectAsState()
    val details by detailsVM.details.collectAsState()
    val comments by detailsVM.comments.collectAsState()

    LaunchedEffect(Unit){
        doubtId?.let { detailsVM.setPostId(it) }
    }

    if(doubtId.isNullOrBlank()){
        navController.popBackStack()
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if(isLoadingPost){
            CircularProgressIndicator()
        }else{
            details?.let { doubtDetailsBox(doubtModel = it)}
            comments.forEach{
                commentComponent(comment = it)
            }
            if(isLoadingComments){
                CircularProgressIndicator()
            }else{
                CustomButton(label = "Load more") {
                    scope.launch{
                        detailsVM.getComments()
                    }
                }
            }
        }
    }

}

