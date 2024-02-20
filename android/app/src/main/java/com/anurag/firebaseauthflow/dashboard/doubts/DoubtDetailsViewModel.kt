package com.anurag.firebaseauthflow.dashboard.doubts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.doubtForm.CommentModel
import com.anurag.firebaseauthflow.doubtForm.DoubtModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DoubtDetailsViewModel: ViewModel() {

    val uid = AuthViewModel().getCurrentUser()?.data?.userId
    private val db = Firebase.firestore
    private val doubtsCollection = db.collection("posts")

    private val _details = MutableStateFlow<DoubtModel?>(null)
    private val _comments = MutableStateFlow<List<CommentModel>>(listOf())
    private val _loadingPost = MutableStateFlow(false)
    private val _loadingComments = MutableStateFlow(false)
    private var query: Query? = null
    private val _itemsPerPage = 20
    private var _postId = ""

    val details = _details.asStateFlow()
    val comments = _comments.asStateFlow()
    val isLoadingPost = _loadingPost.asStateFlow()
    val isLoadingComments = _loadingComments.asStateFlow()


    fun refresh(){
        viewModelScope.launch {
            getDoubtDetails()
        }
    }

    fun setPostId(postId: String){
        if(_postId.isNotEmpty())return
        _postId = postId
        viewModelScope.launch {
            getDoubtDetails()
        }
    }

    suspend fun getDoubtDetails(){
        try {
            _loadingPost.value = true
            if(_postId.isBlank())return
            val res = doubtsCollection.document(_postId).get().await()
            if(res.exists()){
                val obj = res.toObject(DoubtModel::class.java)
                _details.value = obj
                getComments()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        _loadingPost.value = false
    }

    suspend fun getComments() {
        try {
            if(_postId.isBlank())return
            if (query == null) {
                query = doubtsCollection.document(_postId).collection("comments").orderBy("timestamp", Query.Direction.DESCENDING).limit(
                    _itemsPerPage.toLong()
                )
            }
            _loadingComments.value = true
            val res = query!!.get().await()
            val ret = mutableListOf<CommentModel>()
            res.documents.forEach {
                if (it.exists()) {
                    val comment = it.toObject(CommentModel::class.java)
                    if (comment != null) {
                        ret.add(comment)
                    }
                }
            }
            if (res.documents.isNotEmpty()) {
                val lastResult = res.documents.last()
                query = doubtsCollection.document(_postId).collection("comments").orderBy("timestamp", Query.Direction.DESCENDING)
                    .startAfter(lastResult).limit(
                        _itemsPerPage.toLong()
                    )
            }
            var tmp = _comments.value + ret
            _comments.value = tmp
        } catch (e: Exception) {
            e.printStackTrace()
        }
        _loadingComments.value = false
    }
}