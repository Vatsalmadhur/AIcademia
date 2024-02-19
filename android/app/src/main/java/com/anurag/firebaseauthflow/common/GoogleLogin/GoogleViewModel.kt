package com.anurag.firebaseauthflow.common.GoogleLogin

import androidx.lifecycle.ViewModel
import com.anurag.firebaseloginflow.presentation.sign_in.SignInResult
import com.anurag.firebaseloginflow.presentation.sign_in.UserData
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleViewModel() : ViewModel() {
    val auth = Firebase.auth
    suspend fun signWithCredential(credential: AuthCredential): SignInResult {
        return try {
            val user = auth.signInWithCredential(credential).await().user
            SignInResult(status = true, errormessage = null, data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    profilePictureUrl = photoUrl?.toString()
                )
            })
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                status = false, errormessage = e.message, data = null
            )
        }
    }
}