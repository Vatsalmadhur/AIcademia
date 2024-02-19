package com.anurag.firebaseauthflow.dashboard.doubts

import androidx.lifecycle.ViewModel
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.doubtForm.DoubtModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await

class DoubtsListViewModel: ViewModel() {
    val uid = AuthViewModel().getCurrentUser()?.data?.userId
    private val db = Firebase.firestore
    private val doubtsCollection = db.collection("posts")

    private val _currentPage = MutableStateFlow(0)
    private val _itemsPerPage = MutableStateFlow(20)


    suspend fun getPosts(): List<DoubtModel> {
        try{
            val res = doubtsCollection.startAt(_currentPage.value*_itemsPerPage.value).limit(
                _itemsPerPage.value.toLong()
            ).get().await()
            val ret = mutableListOf<DoubtModel>()
            res.documents.forEach{
                if(it!=null && it.exists())
                    it.toObject(DoubtModel::class.java)?.let { it1 -> ret.add(it1) }
            }
            return ret
        }catch (e: Exception){
            e.printStackTrace()
            return listOf()
        }
    }
}