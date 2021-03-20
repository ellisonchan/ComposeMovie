package com.ellison.composemovie.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.material.Card
import androidx.compose.ui.draw.shadow
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ellison.composemovie.R
import com.ellison.composemovie.bean.*

@Preview(showBackground = true)
@Composable
fun StorePreview() {
    Store()
}

@Composable
fun Store() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.sizeIn(16.dp))
        Text(
            modifier = Modifier.padding(6.dp),
            style = MaterialTheme.typography.h6,
            text = stringResource(id = R.string.tab_store_recommend)
        )

        Spacer(Modifier.sizeIn(16.dp))
        MovieGallery(recommendedMovies, width = 220.dp, height = 190.dp)

        CastGroup(cast = testCast1)
        CastGroup(cast = testCast2)

        // Ensure list is totally seen.
        Spacer(Modifier.sizeIn(64.dp))
    }
}

@Composable
fun CastGroup(cast: Cast) {
    Column {
        Spacer(Modifier.sizeIn(32.dp))
        CastCategory(cast)
        Spacer(Modifier.sizeIn(6.dp))
        MovieGallery(cast.movies)
    }
}

@Composable
fun CastCategory(cast: Cast) {
    Row(
        modifier = Modifier
            .height(40.dp)
            .padding(16.dp, 2.dp, 2.dp, 16.dp)
    ) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(50),
            elevation = 8.dp
        ) {
            Image(
                painterResource(id = cast.Icon),
                contentDescription = cast.Name,
            )
        }

        Spacer(Modifier.sizeIn(4.dp))

        Text(
            cast.Name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.wrapContentWidth()
        )
    }
}

@Composable
fun MovieGallery(movies: List<Movie>, width: Dp = 130.dp, height: Dp = 136.dp) {
    LazyRow(modifier = Modifier.padding(top = 2.dp)) {
        items(movies.size) {
            RowItem(
                modifier = Modifier.padding(start = 8.dp),
                width,
                height,
                movie = movies[it]
            )
        }
    }
}

@Composable
fun RowItem(modifier: Modifier, width: Dp = 130.dp, height: Dp = 1306.dp, movie: Movie) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .width(width)
            .height(height + 6.dp)
            .border(0.5.dp, Color.Gray, shape = MaterialTheme.shapes.small)
            .shadow(4.dp),
        shape = MaterialTheme.shapes.small,
    ) {

        Box {
            LoadImage(
                url = movie.Poster,
                modifier = Modifier
                    .width(width)
                    .height(height),
                contentScale = ContentScale.FillBounds,
                contentDescription = movie.Title
            )
            Text(
                text = movie.Title,
                fontSize = 8.sp,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(4.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}