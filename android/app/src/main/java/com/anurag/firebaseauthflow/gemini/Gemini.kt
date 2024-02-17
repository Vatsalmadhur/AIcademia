package com.anurag.firebaseauthflow.gemini

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anurag.firebaseauthflow.dashboard.Home.Prompt
import com.anurag.firebaseauthflow.dashboard.Home.homeViewModel
import com.anurag.firebaseauthflow.firestore.About
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

class Gemini : ViewModel() {

    private val cards = listOf(
        Prompt.RES,
        Prompt.ADVICE,
        Prompt.GOALS,
        Prompt.INTRO,
    )
    private lateinit var bot: Chat
    private lateinit var dataVM: homeViewModel

    private fun generatePrompt(skills: About) =
        "You are working as a personal teacher for a student." +
                " This particular student have already acquired some skills and want to learn some new skill. " +
                "As a personal teacher your job is to respond with valid answers for the questions that your student is asking.\n" +
                "\n" +
                "All answers should be less than 50 words.\n\n" +
                "Answer only what is asked and follow the above actions strictly." +
                "" +
                "Output Instructions: " +
                "1. Only response in plain text." +
                "2. Use three semicolons(;;;) to mark EOL or EO paragraph." +
                "3. Answers should be concise and brief." +
                "\n\nAct as a computer software: give me only the requested output, no conversation, only output in plain text." +
                "\n\n" +
                "Skills Acquired: ${skills.acquired_skills?.joinToString(", ")}\n" +
                "Skills Learning: ${skills.current_skill}"

    private val model: GenerativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = "",
    )

    private fun setBot(skills: About) {
        val prompt = generatePrompt(skills)
        bot = model.startChat(
            history = listOf(
                content(role = "user") {
                    text(prompt)
                },
                content(role = "model") {
                    text("Okay")
                },
            )
        )
    }


    fun setDataVM(x: homeViewModel, skills: About) {
        dataVM = x
        if (skills.current_skill.isNullOrBlank()) return
        setBot(skills)
        getRes(cards.size - 1)
    }

    fun getRes(idx: Int) {
        if (idx < 0) return
        viewModelScope.launch {
            val ret = getInfo(cards[idx].pair.second)
            Log.d("GEMINI", "response: $ret")
//            dataVM.setContent(idx, ret)
            getRes(idx - 1)
        }
    }

    suspend fun getInfo(prompt: String): String {
        return try {
            val response = this.bot.sendMessage(prompt)
            response.text.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) e.printStackTrace()
            "Error generating Response"
        }
    }

    suspend fun send(prompt: String): String {
        return try {
            val response = model.generateContent(prompt)
            response.text.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            ""
        }
    }
}