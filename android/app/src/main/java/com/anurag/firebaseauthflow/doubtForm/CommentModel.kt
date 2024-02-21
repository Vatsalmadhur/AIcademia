package com.anurag.firebaseauthflow.doubtForm

data class CommentModel(
    val body:String = "",
    var uid:String? = "",
    val votes:Int = 0,
    val timestamp:Long = System.currentTimeMillis()
)