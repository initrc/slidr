package io.github.initrc.slidr.feature.portfolio

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.initrc.slidr.core.design.Black60
import io.github.initrc.slidr.core.design.SlidrTheme
import io.github.initrc.slidr.core.design.primaryLinearPainter
import io.github.initrc.slidr.feature.listing.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(
    imageUrls: List<String>,
    onImageClick: (Int) -> Unit,
    modifier: Modifier,
) {
    val state = rememberLazyListState()

    Box(
        modifier = modifier
    ) {
        LazyRow(
            state = state,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = state),
        ) {
            itemsIndexed(
                items = imageUrls,
            ) { index, item ->
                AsyncImage(
                    model = item,
                    contentDescription = stringResource(R.string.image_with_index_content_description, index),
                    contentScale = ContentScale.Crop,
                    placeholder = primaryLinearPainter(),
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable {
                            onImageClick(index)
                        }
                )
            }
        }

        val imageCount = imageUrls.size
        val currentImage by remember { derivedStateOf { state.firstVisibleItemIndex + 1 } }
        Text(
            text = "$currentImage/$imageCount",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomEnd)
                .background(
                    color = Black60,
                    shape = CircleShape,
                )
                .padding(8.dp)
        )
    }
}


@Preview
@Composable
fun PreviewImageSlider() {
    SlidrTheme {
        Surface {
            ImageSlider(
                imageUrls = listOf("", ""),
                onImageClick = {},
                modifier = Modifier
            )
        }
    }
}
