package com.ellison.composemovie.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.ellison.composemovie.R
import com.ellison.composemovie.bean.Movie
import com.ellison.composemovie.bean.favouriteMovies
import com.ellison.composemovie.bean.MoviePro
import com.ellison.composemovie.bean.myAccount
import com.ellison.composemovie.constant.Constants
import com.ellison.composemovie.model.MovieModel
import com.ellison.composemovie.util.Utils

@ExperimentalFoundationApi
@Preview
@Composable
fun Navigation() {
    val movieModel: MovieModel = viewModel()

    val baseTitle = stringResource(id = R.string.app_name)
    val (title, setTitle) = remember { mutableStateOf(baseTitle) }
    val (canPop, setCanPop) = remember { mutableStateOf(false) }

    val navController = rememberNavController()
    navController.addOnDestinationChangedListener { controller, _, _ ->
        setCanPop(controller.previousBackStackEntry != null)
    }

    // Used for check if is movie detail screen.
    val isCurrentMovieDetail = remember { mutableStateOf(false) }

    // Used for get current tab icon to tool bar
    val toolBarIcon = remember { mutableStateOf(Icons.Default.Search) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        color = Color.Blue
                    )
                },
                // Only show back icon when movie detail screen.
                navigationIcon = if (canPop && isCurrentMovieDetail.value) {
                    {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(Icons.Outlined.ArrowBack, "back", tint = Color.Blue)
                        }
                    }
                } else {
                    {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                toolBarIcon.value,
                                "main",
                                tint = Color.Blue
                            )
                        }
                    }
                },
            )
        },

        bottomBar = {
            // Not show bottom navigation if go to movie detail screen.
            if (!isCurrentMovieDetail.value) {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(screen.icon, screen.route) },
                            label = { Text(stringResource(screen.resourceId)) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo = navController.graph.startDestination
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true

                                    toolBarIcon.value = screen.icon

                                    Utils.logDebug(
                                        Utils.TAG_LAUNCH, "BottomNavigation Item click "
                                                + "toolBarIcon:${toolBarIcon.value.name}"
                                    )
                                }
                            },
                            selectedContentColor = Color.Blue,
                            unselectedContentColor = Color.Gray
                        )
                    }
                }
            }
        }
    ) {
        NavHost(navController, startDestination = Screen.Find.route) {
            composable(Screen.Find.route) {
                FindScreen(navController, setTitle, movieModel)
                isCurrentMovieDetail.value = false
            }
            composable(
                route = Constants.ROUTE_DETAIL,
                arguments = listOf(navArgument(Constants.ROUTE_DETAIL_KEY) {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                DetailScreen(
                    backStackEntry.arguments?.getString(Constants.ROUTE_DETAIL_KEY)!!,
                    setTitle,
                    movieModel
                )
                Utils.logDebug(
                    Utils.TAG_LAUNCH,
                    "Navigate to MovieDetail movieDetail:${isCurrentMovieDetail.value}"
                )
                isCurrentMovieDetail.value = true
            }
            composable(Screen.Store.route) {
                StoreScreen(setTitle)
                isCurrentMovieDetail.value = false
            }
            composable(Screen.Favourite.route) {
                FavouriteScreen(setTitle)
                isCurrentMovieDetail.value = false
            }
            composable(Screen.Profile.route) {
                ProfileScreen(setTitle)
                isCurrentMovieDetail.value = false
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FindScreen(
    navController: NavController,
    setTitle: (String) -> Unit,
    movieModel: MovieModel
) {
    setTitle(stringResource(id = R.string.screen_find))
    Find(
        movieModel,
        onClick = { movie ->
            setTitle("")
            navController.navigate(Constants.ROUTE_DETAIL_PRE + movie.imdbID)
        }
    )
}

@Composable
fun DetailScreen(
    movieId: String,
    setTitle: (String) -> Unit,
    movieModel: MovieModel
) {
    val movies: State<List<Movie>> = movieModel.movies.observeAsState(emptyList())
    movies.value.firstOrNull { it.imdbID == movieId }?.let {
        setTitle(it.Title)
        LaunchedEffect(it.imdbID) {
            movieModel.getMovieComposeCoroutines(it.imdbID)
        }
        val moviePro: State<MoviePro> = movieModel.moviePro.observeAsState(MoviePro(it.Title))
        Detail(moviePro.value)
    }
}

@ExperimentalFoundationApi
@Composable
fun StoreScreen(
    setTitle: (String) -> Unit
) {
    setTitle(stringResource(id = R.string.screen_store))
    Store()
}

@ExperimentalFoundationApi
@Composable
fun FavouriteScreen(
    setTitle: (String) -> Unit
) {
    setTitle(stringResource(id = R.string.screen_favourite))
    // Favourite(testMoviePro, onClick = { /*TODO*/ })
    Favourite(favouriteMovies, onClick = { /*TODO*/ })
}

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(
    setTitle: (String) -> Unit
) {
    setTitle(stringResource(id = R.string.screen_profile))
    Profile(myAccount)
}

val items = listOf(
    Screen.Find,
    Screen.Store,
    Screen.Favourite,
    Screen.Profile
)

sealed class Screen(val route: String, val icon: ImageVector, @StringRes val resourceId: Int) {
    object Find : Screen(Constants.ROUTE_FIND, Icons.Default.Search, R.string.tab_find)
    object Store : Screen(Constants.ROUTE_STORE, Icons.Default.Store, R.string.tab_store)
    object Favourite :
        Screen(Constants.ROUTE_FAVOURITE, Icons.Default.Favorite, R.string.tab_favourite)

    object Profile :
        Screen(Constants.ROUTE_PROFILE, Icons.Default.AccountCircle, R.string.tab_profile)
}