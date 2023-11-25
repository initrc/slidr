package io.github.initrc.slidr.core.model

import kotlin.random.Random

data class Message(
    val body: String,
    val isFromMe: Boolean,
    val id: String = Random.nextInt(0, 1_000_000).toString(),
    val isLoading: Boolean = false
)
