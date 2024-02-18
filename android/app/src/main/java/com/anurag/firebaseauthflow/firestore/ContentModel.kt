package com.anurag.firebaseauthflow.firestore

import com.anurag.firebaseauthflow.dashboard.Home.Prompt

data class QuizModel(
    val question:String ="",
    val options:List<String> = listOf(),
    val answer:Int = -1
)

data class Content(
    val intro:String? = null,
    val goals:String? = null,
    val advice:String? = null,
    val resources:String? = null,
    val quiz:List<QuizModel> = listOf()
){
    operator fun iterator(): List<Pair<Prompt, String?>> {
        return listOf(
            Prompt.INTRO to intro,
            Prompt.GOALS to goals,
            Prompt.ADVICE to advice,
            Prompt.RES to resources,

        )
    }
    override operator fun equals(x:Any?):Boolean{
        var ret = true
        this.iterator().forEach{
            if(!it.second.isNullOrEmpty()){
                ret = false
            }
        }
        return ret
    }

    operator fun get(prompt: Prompt): Any? {
        return when(prompt){
            Prompt.INTRO -> intro
            Prompt.RES -> resources
            Prompt.ADVICE -> advice
            Prompt.GOALS -> goals
            Prompt.QUIZ -> quiz
        }
    }
}
