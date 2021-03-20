package com.ellison.composemovie.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ellison.composemovie.bean.Movie
import com.ellison.composemovie.R
import com.ellison.composemovie.bean.testMovies
import com.ellison.composemovie.model.MovieModel
import com.ellison.composemovie.ui.theme.editShapes
import com.ellison.composemovie.ui.theme.itemCardColor
import com.ellison.composemovie.ui.theme.nameColor
import com.ellison.composemovie.ui.theme.darkBlue
import com.ellison.composemovie.ui.theme.pink900
import com.ellison.composemovie.ui.theme.shapes
import com.ellison.composemovie.util.Utils

@ExperimentalFoundationApi
@Composable
fun Find(movieModel: MovieModel, onClick: (Movie) -> Unit) {
    val context = LocalContext.current.applicationContext
    val warningTip = stringResource(id = R.string.input_search_warning)
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var searchQuery by remember { mutableStateOf("") }

    if (!Utils.ensureNetworkAvailable(context, false))
        ShowDialog(R.string.search_dialog_tip, R.string.search_failure)

    Column {
        Row() {
            TextField(
                value = textFieldValue,

                onValueChange = {
                    textFieldValue = it
                    Utils.logDebug(Utils.TAG_SEARCH, "input:$textFieldValue")
                },

                modifier = Modifier
                    // .fillMaxWidth(0.9f)
                    .fillMaxWidth()
                    // .fillMaxHeight(0.15f)
                    .wrapContentHeight()
                    .padding(6.dp),

                textStyle = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Monospace
                ),

                label = {
                    Text(stringResource(id = R.string.input_label_search))
                },

                trailingIcon = {
                    IconButton(
                        onClick = {
                            // Todo search by http
                            Utils.logDebug(
                                Utils.TAG_SEARCH,
                                "search click with keyWord:$textFieldValue"
                            )

                            if (textFieldValue.text.length > 1) {
                                searchQuery = textFieldValue.text
                            } else Toast.makeText(
                                context,
                                warningTip,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    ) {
                        Icon(Icons.Outlined.Search, "search", tint = Color.White)
                    }
                },
                singleLine = true,

                shape = editShapes.large,

                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Blue,
                    focusedLabelColor = Color.Blue,
                    backgroundColor = pink900,
                    cursorColor = Color.White,
                    textColor = Color.White,
                    unfocusedLabelColor = Color.LightGray
                )
            )
        }

        LaunchedEffect(searchQuery) {
            Utils.logDebug(Utils.TAG_SEARCH, "searchQuery updated:$searchQuery")
            if (searchQuery.length > 0) {
                movieModel.searchMoviesComposeCoroutines(searchQuery)
            }
        }
        val moviesData: State<List<Movie>> = movieModel.movies.observeAsState(emptyList())
        val movies = moviesData.value
        val scrollState = rememberLazyListState()

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            // cells = GridCells.Adaptive(minSize = 128.dp),
            cells = GridCells.Fixed(3),
            state = scrollState,
            contentPadding = PaddingValues(2.dp)
        ) {
            items(movies) { movie ->
                MovieThumbnail(movie, onClick = { onClick(movie) })
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MovieThumbnailPreview() {
    MovieThumbnail(movie = testMovies.last(), onClick = {})
}

@Composable
fun MovieThumbnail(movie: Movie, onClick: () -> Unit) {
    Log.d("Compose", "id:${movie.imdbID}")
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .border(0.5.dp, Color.Gray, shape = MaterialTheme.shapes.small)
                .shadow(4.dp),
            shape = shapes.small,
            elevation = 8.dp,
            backgroundColor = itemCardColor
        ) {
            // Vertical layout
            Column(
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .wrapContentSize()
            ) {
                val contentWidth = 100.dp
                val contentHeight = 141.dp

                LoadImage(
                    url = movie.Poster,
                    modifier = Modifier
                        .width(contentWidth)
                        .height(contentHeight),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = movie.Title
                )

                // Spacer(Modifier.sizeIn(2.dp))

                Text(
                    // text = stringResource(id = movie.name),
                    text = movie.Title,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .width(contentWidth)
                        .padding(3.dp),
                    // textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                    color = nameColor,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}