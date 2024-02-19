package com.anurag.firebaseauthflow.firestore

import androidx.lifecycle.ViewModel
import com.anurag.firebaseauthflow.auth.AuthViewModel
import com.anurag.firebaseauthflow.doubtForm.CommentModel
import com.anurag.firebaseauthflow.doubtForm.DoubtModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await



class FSViewModel : ViewModel() {
    val uid = AuthViewModel().getCurrentUser()?.data?.userId
    private val db = Firebase.firestore
    private val usersCollection = db.collection("users")
    private val contentCollection = db.collection("content")
    private val fcmCollection = db.collection("fcm-keys")
    private val doubtsCollection = db.collection("posts")


    suspend fun getAbout(): About? {
        return try {
            val id = uid ?: return About()
            val documentSnapshot = usersCollection.document(id).get().await()
            documentSnapshot.toObject(About::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    suspend fun getContent(): Content? {
        return try {
            val id = uid ?: return null
            val documentSnapshot = contentCollection.document(id).get().await()
            documentSnapshot.toObject(Content::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    data class FCMKeyModel(
        val fcm_key:String = ""
    )

    suspend fun setFcmKey(token:String): Boolean{
        try {
            val id = uid ?: return false
            val obj = FCMKeyModel(token)
            fcmCollection.document(id).set(obj).await()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }


    suspend fun setContent(content: Content): Boolean {
        try {
            val id = uid ?: return false
            contentCollection.document(id).set(content).await()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    suspend fun setAbout(about: About): Boolean {
        try {
            val id = uid ?: return false
            usersCollection.document(id).set(about).await()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    suspend fun updateAcquiredSkills(newSkills: List<String>): Boolean {
        try {
            val id = uid ?: return false
            val doc = usersCollection.document(id)
            if (doc.get().await().exists()) {
                doc.update("acquired_skills", newSkills).await()
                return true
            } else {
                doc.set(About(newSkills, null))
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    suspend fun updateCurrentSkill(newCurrentSkill: String): Boolean {
        try {
            val id = uid ?: return false
            val doc = usersCollection.document(id)
            if (doc.get().await().exists()) {
                doc.update("current_skill", newCurrentSkill).await()
                return true
            } else {
                doc.set(About(null, newCurrentSkill))
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }


}