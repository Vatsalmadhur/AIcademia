package com.anurag.firebaseauthflow.dashboard.Home

sealed class Prompt(val pair: Pair<String, String>) {
    object INTRO : Prompt(
        Pair(
            "Introduction",
            "Question: Give a brief Introduction of what I'm trying to learn and How similar it is to what I know.\n" + "Answer: "
        )
    )

    object GOALS : Prompt(
        Pair(
            "Today's Goals",
            "Question: Set some goals for me to complete today. I can dedicate 2 Hours today.\n" +
                    "ANSWER FORMAT: <GOAL> (approx time required)" + "Answer: "
        )
    )

    object ADVICE : Prompt(
        Pair(
            "Expert's Advice",
            "Question: Give me some advices on how to complete today's goals.\n" + "Answer: "
        )
    )

    object RES : Prompt(
        Pair(
            "Resources",
            "Question: Provide me some resources which I can use to learn what is required to complete today's goals. also provide urls for blogs, books, docs, and videos if needed.\n" + "Answer: "
        )
    )
}