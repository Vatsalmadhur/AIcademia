package com.anurag.firebaseauthflow.dashboard.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anurag.firebaseauthflow.firestore.Content
import com.anurag.firebaseauthflow.firestore.FSViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class homeViewModel : ViewModel() {
    private val _fs = FSViewModel()
    private val _content = MutableStateFlow<Content?>(null)
    val content = _content.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun fetchContent() {
        _isLoading.value = true
        viewModelScope.launch {
            val res = _fs.getContent()
            _content.value = res
            _isLoading.value = false
        }
    }
}