package io.github.initrc.slidr

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SlidrApplication : Application(), ImageLoaderFactory {
    @Inject lateinit var imageLoader: ImageLoader
    override fun newImageLoader() = imageLoader
}
