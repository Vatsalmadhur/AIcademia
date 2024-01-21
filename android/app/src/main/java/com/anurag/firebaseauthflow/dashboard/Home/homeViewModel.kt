package com.anurag.firebaseauthflow.dashboard.Home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class homeViewModel : ViewModel() {
    private val _intro = MutableStateFlow("")
    private val _goals = MutableStateFlow("")
    private val _advice = MutableStateFlow("")
    private val _resources = MutableStateFlow("")


    val intro = _intro.asStateFlow()
    val goals = _goals.asStateFlow()
    val advice = _advice.asStateFlow()
    val resources = _resources.asStateFlow()


    fun setContent(idx: Int, s: String) {
        when (idx) {
            3 -> _intro.value = s
            2 -> _goals.value = s
            1 -> _advice.value = s
            0 -> _resources.value = s
        }
    }
}