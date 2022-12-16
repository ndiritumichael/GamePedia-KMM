package com.devmike.gamepedia.android.ui.features.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.devmike.gamepedia.android.ui.sharedUI.CustomSearchBar
import com.devmike.gamepedia.presenters.GamesPresenters
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(gamesPresenter: GamesPresenters = get()) {
    val gamesData by gamesPresenter.gamesData.collectAsStateWithLifecycle()

    val games = gamesData.pagingData.collectAsLazyPagingItems()
    var searchString by rememberSaveable {
        mutableStateOf("")
    }

    var showSearch by remember {
        mutableStateOf(false)
    }

    // val isFocused = interactionSource.collectIsFocusedAsState()

    val scrollBehavior = if (showSearch){ TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()) } else  TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    LaunchedEffect(key1 = searchString) {
        gamesPresenter.searchGames(searchString)
    }
    val scrollState = rememberLazyListState()

    Scaffold(topBar = {
        AnimatedVisibility(showSearch) {
            TopAppBar(
                title = {
                    Card(
                        modifier = Modifier.padding(8.dp).fillMaxWidth(0.95f),
                        shape = RoundedCornerShape(20.dp)

                    ) {
                        CustomSearchBar(
                            text = searchString,
                            onTextChanged = { searchString = it },
                            leadingIcon = {
                                IconButton(onClick = {
                                    searchString = ""
                                    showSearch = showSearch.not()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            },
                            trailingIcon = {
                                IconButton(onClick = {
                                    searchString = ""
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "clear Search",
                                        modifier = Modifier.padding(end = 8.dp)
                                            .size(20.dp)
                                    )
                                }
                            },
                            placeholderText = "Search for Games"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }

        AnimatedVisibility(!showSearch) {
            LargeTopAppBar(

                title = {
                    Text(text = "Games", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineLarge)
                },
                actions = {
                    AnimatedVisibility(showSearch.not()) {
                        IconButton(onClick = { showSearch = showSearch.not() }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                    }
                },
                scrollBehavior = scrollBehavior

            )
        }
    }, modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)) { padding ->

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, state = scrollState, modifier = Modifier.padding(padding)) {
            items(games) { gam ->
                gam?.let {
                    GameItem(game = it)
                }
            }
            games.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        top = 50.dp,
                                        bottom = 50.dp
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.height(30.dp)
                                )
                            }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(bottom = 56.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(modifier = Modifier.height(30.dp))
                            }
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val errorMessage = games.loadState.refresh as LoadState.Error
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(bottom = 56.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val errorText =
                                        if (errorMessage.error.localizedMessage!!
                                            .contains("404")
                                        ) "Character not Found"
                                        else {
                                            errorMessage.error.localizedMessage
                                        }
                                    Text(errorText)
                                    Button(onClick = { retry() }) {
                                        Text(text = "Try Again")
                                    }
                                }
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val errorMessage = games.loadState.append as LoadState.Error

                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(bottom = 56.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = errorMessage.error.localizedMessage!!)
                                    Button(onClick = { retry() }) {
                                        Text(text = "Try Again")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
