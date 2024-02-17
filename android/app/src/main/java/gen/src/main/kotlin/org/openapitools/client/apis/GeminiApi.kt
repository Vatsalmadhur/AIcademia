package org.openapitools.client.apis

import org.openapitools.client.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import org.openapitools.client.models.ApiResponseModel
import org.openapitools.client.models.GetContentResponse
import org.openapitools.client.models.GetQuizResponse
import org.openapitools.client.models.QueueMessage

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
    fun geminiControllerEnqueueContentGen(@Body queueMessage: QueueMessage): Call<GetQuizResponse>

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
    fun geminiControllerEnqueueQuizGen(@Body queueMessage: QueueMessage): Call<GetQuizResponse>

    /**
     * 
     * 
     * Responses:
     *  - 200: 
     *
     * @param id 
     * @return [Call]<[GetContentResponse]>
     */
    @GET("content")
    fun geminiControllerGetContent(@Query("id") id: kotlin.String): Call<GetContentResponse>

    /**
     * 
     * 
     * Responses:
     *  - 200: 
     *
     * @param id 
     * @return [Call]<[ApiResponseModel]>
     */
    @GET("notify")
    fun geminiControllerNotifyUser(@Query("id") id: kotlin.String): Call<ApiResponseModel>

    /**
     * 
     * 
     * Responses:
     *  - 200: 
     *
     * @param id 
     * @return [Call]<[ApiResponseModel]>
     */
    @GET("update_content")
    fun geminiControllerUpdateContent(@Query("id") id: kotlin.String): Call<ApiResponseModel>

    /**
     * 
     * 
     * Responses:
     *  - 200: 
     *
     * @param id 
     * @return [Call]<[GetQuizResponse]>
     */
    @GET("update_quiz")
    fun geminiControllerUpdateQuiz(@Query("id") id: kotlin.String): Call<GetQuizResponse>

}
