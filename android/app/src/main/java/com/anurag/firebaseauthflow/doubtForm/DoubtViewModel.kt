package com.anurag.firebaseauthflow.doubtForm

import androidx.lifecycle.ViewModel
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.common.Searchbar.SearchViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class DoubtViewModel : ViewModel() {
    private val _title = MutableStateFlow("")
    private val _desc = MutableStateFlow("")
    private val _loading = MutableStateFlow(false)
    private val _prevResStatus = MutableStateFlow(true)

    val uid = AuthViewModel().getCurrentUser()?.data?.userId
    private val db = Firebase.firestore
    private val doubtsCollection = db.collection("posts")

    val title = _title.asStateFlow()
    val desc = _desc.asStateFlow()
    val searchVM = SearchViewModel()
    val loading = _loading.asStateFlow()
    val prevStatus = _prevResStatus.asStateFlow()


    fun setTitle(str: String) {
        _title.value = str
    }

    fun setDesc(str: String) {
        _desc.value = str
    }

    fun clear() {}

    private suspend fun postDoubtToDB(doubt: DoubtModel): Boolean {
        try {
            val id = uid ?: return false
            doubt.uid = id
            doubtsCollection.document().set(doubt).await()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

    suspend fun PostDoubt(): Boolean {
        _loading.value = true
        val doubtModel = DoubtModel(
            title = _title.value,
            desc = _desc.value,
            tags = searchVM.selected.value.toList()
        )
        val res = postDoubtToDB(doubtModel)
        _prevResStatus.value = res
        _loading.value = false
        return res
    }


}