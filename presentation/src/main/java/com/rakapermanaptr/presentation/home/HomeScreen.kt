package com.rakapermanaptr.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rakapermanaptr.domain.home.entity.Pokemon
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeViewEvent.Initial)
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeViewEffect.ShowErrorMessage -> snackbarHostState.showSnackbar(
                    message = effect.errMsg
                )
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 8.dp),
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(state.pokemonList) { index, item ->
                val isEven = index % 2 == 0
                CardPokemon(item, isEven)

                if (index >= state.pokemonList.size - 5 && state.hasNextPage) {
                    LaunchedEffect(index) {
                        viewModel.onEvent(HomeViewEvent.LoadPokemons)
                    }
                }
            }

            if (state.isLoading) {
                item(span = { GridItemSpan(2) }) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CardPokemon(pokemon: Pokemon, isEvent: Boolean) {
    val number = pokemon.url.trimEnd('/').split("/").last()

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isEvent) {
                Text(text = pokemon.name)
                AsyncImage(
                    modifier = Modifier.size(80.dp),
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png",
                    contentDescription = null
                )
            } else {
                AsyncImage(
                    modifier = Modifier.size(80.dp),
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png",
                    contentDescription = null
                )
                Text(text = pokemon.name)
            }
        }
    }
}