# GeminiApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**geminiControllerGetContent**](GeminiApi.md#geminiControllerGetContent) | **GET** content | 
[**geminiControllerUpdateContent**](GeminiApi.md#geminiControllerUpdateContent) | **GET** update_content | 





### Example
```kotlin
// Import classes:
//import org.openapitools.client.*
//import org.openapitools.client.infrastructure.*
//import com.anurag.firebaseauthflow.api.client.model.*

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
//import com.anurag.firebaseauthflow.api.client.model.*

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

