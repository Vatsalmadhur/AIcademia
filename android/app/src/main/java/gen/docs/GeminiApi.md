# GeminiApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**geminiControllerEnqueueContentGen**](GeminiApi.md#geminiControllerEnqueueContentGen) | **POST** enqueue_content | 
[**geminiControllerEnqueueQuizGen**](GeminiApi.md#geminiControllerEnqueueQuizGen) | **POST** enqueue_quiz | 
[**geminiControllerGetContent**](GeminiApi.md#geminiControllerGetContent) | **GET** content | 
[**geminiControllerNotifyUser**](GeminiApi.md#geminiControllerNotifyUser) | **GET** notify | 
[**geminiControllerUpdateContent**](GeminiApi.md#geminiControllerUpdateContent) | **GET** update_content | 
[**geminiControllerUpdateQuiz**](GeminiApi.md#geminiControllerUpdateQuiz) | **GET** update_quiz | 





### Example
```kotlin
// Import classes:
//import org.openapitools.client.*
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

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
//import org.openapitools.client.models.*

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




### Example
```kotlin
// Import classes:
//import org.openapitools.client.*
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(GeminiApi::class.java)
val id : kotlin.String = id_example // kotlin.String | 

val result : GetContentResponse = webService.geminiControllerGetContent(id)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **kotlin.String**|  |

### Return type

[**GetContentResponse**](GetContentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json




### Example
```kotlin
// Import classes:
//import org.openapitools.client.*
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(GeminiApi::class.java)
val id : kotlin.String = id_example // kotlin.String | 

val result : ApiResponseModel = webService.geminiControllerNotifyUser(id)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **kotlin.String**|  |

### Return type

[**ApiResponseModel**](ApiResponseModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json




### Example
```kotlin
// Import classes:
//import org.openapitools.client.*
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(GeminiApi::class.java)
val id : kotlin.String = id_example // kotlin.String | 

val result : ApiResponseModel = webService.geminiControllerUpdateContent(id)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **kotlin.String**|  |

### Return type

[**ApiResponseModel**](ApiResponseModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json




### Example
```kotlin
// Import classes:
//import org.openapitools.client.*
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiClient = ApiClient()
val webService = apiClient.createWebservice(GeminiApi::class.java)
val id : kotlin.String = id_example // kotlin.String | 

val result : GetQuizResponse = webService.geminiControllerUpdateQuiz(id)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **kotlin.String**|  |

### Return type

[**GetQuizResponse**](GetQuizResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

