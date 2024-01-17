package com.anurag.firebaseauthflow.dashboard.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anurag.firebaseauthflow.firestore.About
import com.anurag.firebaseauthflow.firestore.FSViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SkillsViewModel: ViewModel() {
    val _skills = MutableStateFlow(About())
    val skills = _skills.asStateFlow()

    val fs = FSViewModel()

    init {
        viewModelScope.launch {
            _skills.value = fs.getAbout() ?: About()
        }
    }
}