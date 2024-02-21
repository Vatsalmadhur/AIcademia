package com.anurag.firebaseauthflow.dashboard.doubts

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.anurag.firebaseauthflow.common.CustomButton
import com.anurag.firebaseauthflow.common.addComment.AddComment
import com.anurag.firebaseauthflow.common.commentComp.Comment
import com.anurag.firebaseauthflow.doubtDetailsBox.doubtDetailsBox
import com.anurag.firebaseauthflow.doubtForm.CommentModel
import kotlinx.coroutines.launch

@Composable
fun DoubtDetails(doubtId: String?, navController: NavHostController) {

    val scope = rememberCoroutineScope()
    val detailsVM:DoubtDetailsViewModel = viewModel()
    val isLoadingPost by detailsVM.isLoadingPost.collectAsState()
    val isLoadingComments by detailsVM.isLoadingComments.collectAsState()
    val details by detailsVM.details.collectAsState()
    val comments by detailsVM.comments.collectAsState()
    val ctx = LocalContext.current

    LaunchedEffect(Unit){
        doubtId?.let { detailsVM.setPostId(it) }
    }

    if(doubtId.isNullOrBlank()){
        navController.popBackStack()
        return
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp, 0.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
        ) {
        if(isLoadingPost){
            CircularProgressIndicator()
        }else{
            details?.let { doubtDetailsBox(doubtModel = it)}
            AddComment(onSubmit = {
                scope.launch {
                    val res = detailsVM.postComment(CommentModel(body = it))
                    Toast.makeText(ctx,if(res) "Answer posted!" else "some error occurred" , Toast.LENGTH_LONG).show()
                }
            })
            comments.forEach{
                Comment(comment = it)
            }
            if(isLoadingComments){
                CircularProgressIndicator()
            }else{
               Box(modifier = Modifier.padding(start = 8.dp)) {
                   CustomButton(label = "Load more") {
                       scope.launch {
                           detailsVM.getComments()
                       }
                   }
               }
            }
        }
    }

}

