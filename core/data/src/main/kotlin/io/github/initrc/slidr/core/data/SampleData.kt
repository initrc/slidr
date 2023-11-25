package io.github.initrc.slidr.core.data

import io.github.initrc.slidr.core.model.Message

object SampleData {
    val chatbotConversation = listOf(
        Message(
            "What's the weather like tomorrow?",
            true,
        ),
        Message(
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim(),
            false,
        ),
    )
}
