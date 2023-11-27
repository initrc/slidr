package io.github.initrc.slidr.core.data

import io.github.initrc.slidr.core.model.Portfolio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PortfolioRepository {

    private val imageUrls = listOf(
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2022-4-16x.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2021-11-25x.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2021-10-1x.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2021-8-25x.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2021-8-20x.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2021-6-5.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2021-4-3.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2019-11-14.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2019-11-9.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2019-1-10x.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2019-1-5.jpg",
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2017-6-9.jpg",
    )

    private val avatarImageUrl =
        "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-avatar.jpg"

    private val portfolioSample = Portfolio(
        id = "kyz",
        name = "Yizheng Ke",
        bio = "Digital painting in oil painting style #portrait #landscape",
        imageUrls = imageUrls,
        avatarImageUrl = avatarImageUrl,
        location = "Shanghai, China",
        rating = 4.88,
        cost = 199,
    )

    fun getPortfolio(id: String): Flow<Portfolio> = flow {
        if (id == "kyz") emit(portfolioSample)
    }
}
