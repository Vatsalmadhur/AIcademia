package com.anurag.firebaseauthflow.common.Quiz

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel: ViewModel() {
    private val _answersMarked = MutableStateFlow(List(5, init = {-1} ))
    val answersMatked = _answersMarked.asStateFlow()

    fun markAnswer(idx:Int, option:Int){
        val tmp = _answersMarked.value.toMutableList()
        tmp[idx] = option
      _answersMarked.value = tmp
    }
}