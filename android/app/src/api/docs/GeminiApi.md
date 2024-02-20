# GeminiApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**geminiControllerEnqueueContentGen**](GeminiApi.md#geminiControllerEnqueueContentGen) | **POST** enqueue_content | 
[**geminiControllerEnqueueQuizGen**](GeminiApi.md#geminiControllerEnqueueQuizGen) | **POST** enqueue_quiz | 





### Example
```kotlin
// Import classes:
//import org.openapitools.client.*
//import org.openapitools.client.infrastructure.*
//import com.anurag.firebaseauthflow.api.client.model.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(GeminiApi::class.java)
val queueMessage : QueueMessage =  // QueueMessage | 

val result : GetQuizResponse = webService.geminiControllerEnqueueContentGen(queueMessage)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **queueMessage** | [**QueueMessage**](QueueMessage.md)|  |

### Return type

[**GetQuizResponse**](GetQuizResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json




### Example
```kotlin
// Import classes:
//import org.openapitools.client.*
//import org.openapitools.client.infrastructure.*
//import com.anurag.firebaseauthflow.api.client.model.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(GeminiApi::class.java)
val queueMessage : QueueMessage =  // QueueMessage | 

val result : GetQuizResponse = webService.geminiControllerEnqueueQuizGen(queueMessage)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **queueMessage** | [**QueueMessage**](QueueMessage.md)|  |

### Return type

[**GetQuizResponse**](GetQuizResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

