package io.github.initrc.slidr.feature.chat.data

import io.github.initrc.slidr.feature.chat.ExpandableMessage
import io.github.initrc.slidr.feature.chat.Message

/**
 * SampleData for Jetpack Compose Tutorial 
 */
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

    // Sample conversation data
    val expandableConversation = listOf(
        ExpandableMessage(
            "Lexi",
            "Test...Test...Test..."
        ),
        ExpandableMessage(
            "Lexi",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        ExpandableMessage(
            "Lexi",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        ExpandableMessage(
            "Lexi",
            "Searching for alternatives to XML layouts..."
        ),
        ExpandableMessage(
            "Lexi",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        ExpandableMessage(
            "Lexi",
            "It's available from API 21+ :)"
        ),
        ExpandableMessage(
            "Lexi",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        ExpandableMessage(
            "Lexi",
            "Android Studio next version's name is Arctic Fox"
        ),
        ExpandableMessage(
            "Lexi",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        ExpandableMessage(
            "Lexi",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        ExpandableMessage(
            "Lexi",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        ExpandableMessage(
            "Lexi",
            "Previews are also interactive after enabling the experimental setting"
        ),
        ExpandableMessage(
            "Lexi",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}
