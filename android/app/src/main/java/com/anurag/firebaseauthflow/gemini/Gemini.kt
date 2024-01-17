package com.anurag.firebaseauthflow.gemini

import com.anurag.firebaseauthflow.firestore.About
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

class Gemini(scope: CoroutineScope) {
    private val model: GenerativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = "",
    )
    private lateinit var bot: Chat
    private val scope: CoroutineScope
    private var retry = 3;
    private var initOnce: Boolean = false

    init {
        this.scope = scope
    }

    fun setSkills(skills: About) {
        if (skills.current_skill == null) return
        else {
            if (!this.initOnce) {
                this.initOnce = true
                this.bot = model.startChat(
                    history = listOf(
                        content(role = "user") { text("You are given a list of a skills a person knows and given a skill a person wants to learn. based on the above information you are expected to respond with valid information, introduction, resources, today's goal and expert advice. You will only respond when the question matches above list of questions. You will act like an API server which responds with text only. The Introduction will be of 50 words, today's goal will a topic related to subject which I'm learning. Advice should be of 60 to 100 words. Resources can contain various links for blogs, portals, articles, youtube video links, channel name, books and authors, etc. In all the responses use ';;;' in place of new line.") },
                        content(role = "model") {
                            text("okay, I'll only reply the answer as plain text and responds like an api and give valid information.}")
                        },
                        content(role = "user") { text("The set of known skills are ${skills.acquired_skills.toString()}. The skills I want to learn is ${skills.current_skill}") },
                        content(role = "model") { text("Okay, I understand the requirements and I'm going in APO mode from now on.") }
                    )

                )
            }
        }
    }

    fun getRes(req: String): List<String> {
        var ret = ""
        scope.launch {
            ret = getInfo(req)
        }
        return ret.split(";;;")
    }

    suspend fun getInfo(req: String): String {
        return try {
            if (retry == 0) return "Error generating response"
            this.retry -= 1
            val response = this.bot.sendMessage("Send me $req.")
            response.text.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
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