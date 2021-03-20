package com.ellison.composemovie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.ellison.composemovie.ui.theme.compositedOnSurface
import com.ellison.composemovie.util.Utils
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState

@Composable
fun LoadImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    placeholderColor: Color? = MaterialTheme.colors.compositedOnSurface(0.2f)
) {
    CoilImage(
        data = url,
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
        fadeIn = true,
        onRequestCompleted = {
            when (it) {
                is ImageLoadState.Success -> Utils.logDebug(
                    Utils.TAG_NETWORK,
                    "Image succeed with source:${it.source}"
                )
                is ImageLoadState.Error -> Utils.logDebug(
                    Utils.TAG_NETWORK,
                    "Image error msg:${it.throwable.message}"/*, it.throwable*/
                )
                ImageLoadState.Loading -> Utils.logDebug(Utils.TAG_NETWORK, "Image loading")
                ImageLoadState.Empty -> Utils.logDebug(Utils.TAG_NETWORK, "Image empty")
            }
        },
//        error = {
//            Image(painterResource(R.drawable.ic_error), contentDescription = "Error")
//        },
        loading = {
            if (placeholderColor != null) {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(placeholderColor)
                )
            }
        }
    )
}