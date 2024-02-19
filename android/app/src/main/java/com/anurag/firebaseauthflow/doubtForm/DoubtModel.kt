package com.anurag.firebaseauthflow.doubtForm

data class DoubtModel(
    var uid:String? = "",
    val title:String="",
    val desc:String="",
    val tags:List<String> = listOf()
)
