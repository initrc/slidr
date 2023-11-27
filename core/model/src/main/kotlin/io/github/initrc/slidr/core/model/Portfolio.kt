package io.github.initrc.slidr.core.model

data class Portfolio(
    val id: String,
    val name: String,
    val bio: String,
    val imageUrls: List<String>,
    val avatarImageUrl: String,
    val location: String,
    val rating: Double,
    val cost: Int,
)