package com.anurag.firebaseauthflow.auth

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.anurag.firebaseauthflow.R
import com.anurag.firebaseloginflow.presentation.sign_in.SignInResult
import com.anurag.firebaseloginflow.presentation.sign_in.UserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth

    suspend fun signin(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSigninRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException)
                throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val creds = oneTapClient.getSignInCredentialFromIntent(intent)
        val idToken = creds.googleIdToken;
        val googleCreds = GoogleAuthProvider.getCredential(idToken, null)
        return try {
            val user = auth.signInWithCredential(googleCreds).await().user
            SignInResult(
                status = true,
                errormessage = null,
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        email = email,
                        profilePictureUrl = photoUrl?.toString()
                    )
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                status = false,
                errormessage = e.message, data = null
            )
        }
    }

    suspend fun signout() {
        try {
            oneTapClient.signOut()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }


    suspend fun getCurrentUser(): UserData? {
        return auth.currentUser?.run {
            UserData(
                userId = uid,
                username = displayName,
                email = email,
                profilePictureUrl = photoUrl?.toString()
            )
        }
    }

    private fun buildSigninRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.Builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.WEB_CLIENT_ID))
                    .build()
            ).setAutoSelectEnabled(true).build()
    }
}