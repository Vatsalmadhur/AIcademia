package com.anurag.firebaseauthflow.api.client.api

import org.openapitools.client.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call

import com.anurag.firebaseauthflow.api.client.model.ApiResponseModel
import com.anurag.firebaseauthflow.api.client.model.GetQuizResponse
import com.anurag.firebaseauthflow.api.client.model.QueueMessage

interface GeminiApi {
    /**
     * 
     * 
     * Responses:
     *  - 200: 
     *  - 201: 
     *
     * @param queueMessage 
     * @return [Call]<[GetQuizResponse]>
     */
    @POST("enqueue_content")
    fun geminiControllerEnqueueContentGen(@Body queueMessage: QueueMessage): Call<ApiResponseModel>

    /**
     * 
     * 
     * Responses:
     *  - 200: 
     *  - 201: 
     *
     * @param queueMessage 
     * @return [Call]<[GetQuizResponse]>
     */
    @POST("enqueue_quiz")
    fun geminiControllerEnqueueQuizGen(@Body queueMessage: QueueMessage): Call<ApiResponseModel>

}
