package com.anurag.firebaseauthflow.common.Quiz

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel : ViewModel() {
    private val _answersMarked = MutableStateFlow(List(5, init = { -1 }))
    val answersMarked = _answersMarked.asStateFlow()
    private val _isReveal = MutableStateFlow(false)
    val isReveal = _isReveal.asStateFlow()
    private val _isLocked = MutableStateFlow(false)
    val isLocked = _isLocked.asStateFlow()

    fun markAnswer(idx: Int, option: Int) {
        if (_isLocked.value) return
        val tmp = _answersMarked.value.toMutableList()
        tmp[idx] = option
        _answersMarked.value = tmp
    }

    fun saveAnswers() {
        _isReveal.value = !_isReveal.value
    }

    fun clear() {
        _answersMarked.value = List(5, init = { -1 })
        _isLocked.value = false
        _isReveal.value = false
    }

    fun lockAnswers() {
        _isLocked.value = true
    }
}