package com.anurag.firebaseauthflow.dashboard.Home

import androidx.lifecycle.ViewModel
import com.anurag.firebaseauthflow.api.client.api.GeminiApi
import com.anurag.firebaseauthflow.api.client.model.ApiResponseModel
import com.anurag.firebaseauthflow.api.client.model.QueueMessage
import com.anurag.firebaseauthflow.auth.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.openapitools.client.infrastructure.ApiClient
import retrofit2.await

class GeminiViewModel:ViewModel() {
    val uid = AuthViewModel().getCurrentUser()?.data?.userId
    private val apiClient = ApiClient(baseUrl = "https://aicademia.madhurvatsal.tech/server")
    private val webService = apiClient.createService(GeminiApi::class.java)
    private val _quizLoading = MutableStateFlow(false)
    private val _contentLoading = MutableStateFlow(false)

    val isQuizLoading = _quizLoading.asStateFlow()
    val isContentLoading = _contentLoading.asStateFlow()


    suspend fun enqueueContent(): ApiResponseModel? {
        val id = uid ?: return null
        var ret:ApiResponseModel? = null
        try{
            _contentLoading.value = true
            val queueMessage =  QueueMessage(id)
            val result : ApiResponseModel = webService.geminiControllerEnqueueContentGen(queueMessage).await()
            ret = result
        }catch(e:Exception){
            e.printStackTrace()
        }finally {
            _contentLoading.value = false
        }
        return ret

    }
    suspend fun enqueueQuiz(): ApiResponseModel? {
        val id = uid ?: return null
        var ret:ApiResponseModel? = null
        try{
            _quizLoading.value = true
            val queueMessage =  QueueMessage(id)
            val result : ApiResponseModel = webService.geminiControllerEnqueueQuizGen(queueMessage).await()
            ret = result
        }catch(e:Exception){
            e.printStackTrace()
        }finally {
            _quizLoading.value = false
        }
        return ret

    }
}