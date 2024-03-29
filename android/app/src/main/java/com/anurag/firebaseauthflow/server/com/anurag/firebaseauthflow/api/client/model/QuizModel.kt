/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.anurag.firebaseauthflow.api.client.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param question 
 * @param options 
 * @param answer 
 */


data class QuizModel (

    @Json(name = "question")
    val question: kotlin.String,

    @Json(name = "options")
    val options: kotlin.collections.List<kotlin.String>,

    @Json(name = "answer")
    val answer: java.math.BigDecimal

)

