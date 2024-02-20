package com.anurag.firebaseauthflow.dashboard.doubts

import androidx.lifecycle.ViewModel
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.doubtForm.DoubtModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class DoubtsListViewModel : ViewModel() {
    val uid = AuthViewModel().getCurrentUser()?.data?.userId
    private val db = Firebase.firestore
    private val doubtsCollection = db.collection("posts")

    private val _posts = MutableStateFlow<List<DoubtModel>>(listOf())
    private val _loading = MutableStateFlow(false)
    private var query: Query? = null
    private val _itemsPerPage = 20

    val posts = _posts.asStateFlow()
    val isLoading = _loading.asStateFlow()


    suspend fun getPosts() {
        try {
            if (query == null) {
                query = doubtsCollection.orderBy("timestamp", Query.Direction.DESCENDING).limit(
                    _itemsPerPage.toLong()
                )
            }
            _loading.value = true
            val res = query!!.get().await()
            val ret = mutableListOf<DoubtModel>()
            res.documents.forEach {
                if (it.exists()) {
                    val doubt = it.toObject(DoubtModel::class.java)
                    if (doubt != null) {
                        doubt.postId = it.id
                        ret.add(doubt)
                    }
                }
            }
            if (res.documents.isNotEmpty()) {
                val lastResult = res.documents.last()
                query = doubtsCollection.orderBy("timestamp", Query.Direction.DESCENDING)
                    .startAfter(lastResult).limit(
                        _itemsPerPage.toLong()
                    )
            }
            var tmp = _posts.value + ret
            _posts.value = tmp
        } catch (e: Exception) {
            e.printStackTrace()
        }
        _loading.value = false
    }
}