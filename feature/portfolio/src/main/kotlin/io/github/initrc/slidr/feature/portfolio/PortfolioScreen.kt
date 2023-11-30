package io.github.initrc.slidr.feature.portfolio

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import io.github.initrc.slidr.core.design.primaryLinearPainter
import io.github.initrc.slidr.core.design.SlidrTheme
import io.github.initrc.slidr.core.model.Portfolio

@Composable
fun PortfolioScreen(
    portfolioViewModel: PortfolioViewModel = hiltViewModel(),
    onChatClick: (String) -> Unit,
    onImageClick: (Int) -> Unit,
) {
    val uiState by portfolioViewModel.uiState.collectAsStateWithLifecycle()
    PortfolioScreen(
        uiState = uiState,
        onChatClick = onChatClick,
        onImageClick = onImageClick,
    )
}

@Composable
fun PortfolioScreen(
    uiState: PortfolioUiState,
    onChatClick: (String) -> Unit,
    onImageClick: (Int) -> Unit,
) {
    val isLoading = uiState is PortfolioUiState.Loading
    val isSuccess = uiState is PortfolioUiState.Success
    Surface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = isLoading,
                modifier = Modifier.align(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }

            if (isSuccess) {
                PortfolioView(
                    portfolio = (uiState as PortfolioUiState.Success).portfolio,
                    onChatClick = onChatClick,
                    onImageClick = onImageClick,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
fun PortfolioView(
    portfolio: Portfolio,
    onChatClick: (String) -> Unit,
    onImageClick: (Int) -> Unit,
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp)
        ) {
            ImageSlider(
                imageUrls = portfolio.imageUrls,
                onImageClick = onImageClick,
                modifier = Modifier.aspectRatio(0.8f) )
            Text(
                text = portfolio.bio,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(24.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp)
            ) {
                AsyncImage(
                    model = portfolio.avatarImageUrl,
                    contentDescription = "avatar",
                    placeholder = primaryLinearPainter(),
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                )
                Column {
                    Text(
                        text = portfolio.name,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
            Divider(modifier = Modifier.padding(24.dp))
            PortfolioIconAndText(
                icon = Icons.Rounded.Star,
                text = "${portfolio.rating} · ${portfolio.imageUrls.size} artworks",
                modifier = Modifier.padding(start = 24.dp, end = 24.dp)
            )
            PortfolioIconAndText(
                icon = Icons.Rounded.LocationOn,
                text = portfolio.location,
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 4.dp)
            )
        }
        ContactBar(
            portfolio = portfolio,
            onClick = { onChatClick(portfolio.id) },
            modifier = Modifier
                .height(64.dp)
        )
    }
}

@Composable
fun PortfolioIconAndText(icon: ImageVector, text: String, modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "location",
            modifier = Modifier
                .padding(end = 4.dp)
                .size(24.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun ContactBar(
    portfolio: Portfolio,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Divider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Text(
                text = "$${portfolio.cost} / class",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { onClick() },
            ) {
                Text("Chat")
            }
        }
    }
}

@Preview
@Composable
fun PreviewPortfolioView() {
    SlidrTheme {
        Surface {
            PortfolioView(
                portfolio = Portfolio(
                    id = "kyz",
                    name = "Yizheng Ke",
                    bio = "Digital painting in oil painting style #portrait #landscape",
                    imageUrls = listOf("", ""),
                    avatarImageUrl = "",
                    location = "Shanghai, China",
                    rating = 4.88,
                    cost = 199,
                ),
                onChatClick = {},
                onImageClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
