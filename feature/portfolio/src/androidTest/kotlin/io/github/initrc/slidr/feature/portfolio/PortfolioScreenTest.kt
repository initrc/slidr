package io.github.initrc.slidr.feature.portfolio

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import coil.Coil
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import io.github.initrc.slidr.feature.listing.R
import io.github.initrc.slidr.core.design.SlidrTheme
import io.github.initrc.slidr.core.model.Portfolio
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val IMAGE_URL = "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-2022-4-16x.jpg"
private const val AVATAR_IMAGE_URL = "https://raw.githubusercontent.com/initrc/asset/main/images/art/yizheng-ke-avatar.jpg"
class PortfolioScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val portfolio = Portfolio(
        id = "kyz",
        name = "Yizheng Ke",
        bio = "Digital painting in oil painting style #portrait #landscape",
        imageUrls = listOf(IMAGE_URL),
        avatarImageUrl = AVATAR_IMAGE_URL,
        location = "Shanghai, China",
        rating = 4.88,
        cost = 199,
    )

    @OptIn(ExperimentalCoilApi::class)
    @Before
    fun before() {
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(IMAGE_URL, ColorDrawable(Color.RED))
            .intercept(AVATAR_IMAGE_URL, ColorDrawable(Color.RED))
            .default(ColorDrawable(Color.BLUE))
            .build()
        val imageLoader = ImageLoader.Builder(context)
            .components { add(engine) }
            .build()
        Coil.setImageLoader(imageLoader)
    }

    @Test
    fun testChatClicked() {
        var chatClicked = false
        val portfolioUiState = PortfolioUiState.Success(portfolio)
        composeTestRule.setContent {
            SlidrTheme {
                PortfolioScreen(
                    uiState = portfolioUiState,
                    onChatClick = { chatClicked = true },
                    onImageClick = {}
                )
            }
        }
        composeTestRule.onNodeWithText(context.getString(R.string.chat)).performClick()
        assertTrue(chatClicked)
    }

    @Test
    fun testImageClicked() {
        var indexOfImageClicked = -1
        val portfolioUiState = PortfolioUiState.Success(portfolio)
        composeTestRule.setContent {
            SlidrTheme {
                PortfolioScreen(
                    uiState = portfolioUiState,
                    onChatClick = {},
                    onImageClick = { indexOfImageClicked = it }
                )
            }
        }
        val contentDescription = context.getString(R.string.image_with_index_content_description, 0)
        composeTestRule.onNodeWithContentDescription(contentDescription).performClick()
        assertEquals(0, indexOfImageClicked)
    }
}