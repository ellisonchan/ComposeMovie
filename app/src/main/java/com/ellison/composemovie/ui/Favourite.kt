package com.ellison.composemovie.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ellison.composemovie.bean.*
import com.ellison.composemovie.ui.theme.*

@Preview(showBackground = true)
@Composable
fun FavouritePreview() {
    Favourite(moviePros = favouriteMovies, onClick = { })
}

@Composable
fun Favourite(moviePros: List<MoviePro>, onClick: () -> Unit) {
    LazyColumn(modifier = Modifier.padding(top = 2.dp)) {
        items(moviePros.size) {
            LikeItem(
                moviePro = moviePros[it],
                onClick
            )
        }
    }

}

@Composable
fun LikeItem(moviePro: MoviePro, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.small)
                .shadow(4.dp),
            shape = shapes.small,
            elevation = 8.dp,
            backgroundColor = itemCardColor
        ) {
            // Horizontal layout
            Row(
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LoadImage(
                    url = moviePro.Poster,
                    modifier = Modifier
                        .width(80.dp)
                        .height(100.dp),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = moviePro.Title
                )

                Spacer(Modifier.sizeIn(16.dp))

                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = moviePro.Title,
                        style = MaterialTheme.typography.h6,
                        color = nameColor,
                    )

                    Text(
                        text = moviePro.Type,
                        style = MaterialTheme.typography.caption,
                        color = nameColor,
                    )

                    Text(
                        text = moviePro.Year,
                        style = MaterialTheme.typography.caption,
                        color = nameColor,
                    )
                }
            }
        }
    }
}
