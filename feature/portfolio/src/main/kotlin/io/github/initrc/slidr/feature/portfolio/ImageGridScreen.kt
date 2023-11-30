package io.github.initrc.slidr.feature.portfolio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import io.github.initrc.slidr.core.design.primaryLinearPainter
import io.github.initrc.slidr.feature.listing.R
import kotlinx.coroutines.launch


@Composable
fun ImageGridScreen(
    portfolioViewModel: PortfolioViewModel,
    imageIndex: Int,
) {
    val uiState by portfolioViewModel.uiState.collectAsStateWithLifecycle()
    (uiState as? PortfolioUiState.Success)?.let { successUiState ->
        ImageGridScreen(
            imageUrls = successUiState.portfolio.imageUrls,
            imageIndex = imageIndex
        )
    }
}

@Composable
fun ImageGridScreen(
    imageUrls: List<String>,
    imageIndex: Int,
) {
    Surface {
        val gridState = rememberLazyStaggeredGridState()
        val coroutineScope = rememberCoroutineScope()
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(200.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp,
            content = {
                itemsIndexed(imageUrls) { index, item ->
                    AsyncImage(
                        model = item,
                        contentDescription = stringResource(R.string.image_with_index_content_description, index),
                        placeholder = primaryLinearPainter(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                }
                coroutineScope.launch {
                    gridState.scrollToItem(imageIndex)
                }
            },
            state = gridState,
            modifier = Modifier.fillMaxSize()
        )
    }
}
