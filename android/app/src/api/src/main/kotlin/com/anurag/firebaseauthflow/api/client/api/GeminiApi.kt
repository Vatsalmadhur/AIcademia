package com.anurag.firebaseauthflow.api.client.api

import org.openapitools.client.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.squareup.moshi.Json

import com.anurag.firebaseauthflow.api.client.model.ApiResponseModel
import com.anurag.firebaseauthflow.api.client.model.GetContentResponse

interface GeminiApi {
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
    @GET("update_content")
    fun geminiControllerUpdateContent(@Query("id") id: kotlin.String): Call<ApiResponseModel>

}
