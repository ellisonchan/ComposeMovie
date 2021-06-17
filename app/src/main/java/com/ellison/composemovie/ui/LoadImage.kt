package com.ellison.composemovie.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ellison.composemovie.R
import com.ellison.composemovie.ui.theme.compositedOnSurface
import com.ellison.composemovie.util.Utils
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun LoadImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    placeholderColor: Color? = MaterialTheme.colors.compositedOnSurface(0.2f)
) {
    val coilPainter = rememberCoilPainter(request = url )
    Image(
        painter = coilPainter,
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
//        fadeIn = true,
    )
    Crossfade(coilPainter.loadState) { state ->
        when (state) {
            is ImageLoadState.Loading -> {
                Utils.logDebug(Utils.TAG_NETWORK, "Image loading")
                if (placeholderColor != null) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(placeholderColor)
                    )
                }
            }
            is ImageLoadState.Error -> {
                Utils.logDebug(
                    Utils.TAG_NETWORK,
                    "Image error msg: "/*, it.throwable*/
                )
                Image(painterResource(R.drawable.ic_error), contentDescription = "Error")
            }
            is ImageLoadState.Success -> {
                Utils.logDebug(
                    Utils.TAG_NETWORK,
                    "Image succeed with source:${coilPainter }"
                )
            }
            ImageLoadState.Empty -> Utils.logDebug(Utils.TAG_NETWORK, "Image empty")
        }
    }
}