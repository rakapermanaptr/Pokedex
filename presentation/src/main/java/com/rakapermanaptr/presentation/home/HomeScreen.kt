package com.rakapermanaptr.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rakapermanaptr.domain.home.entity.Pokemon
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(paddingValues: PaddingValues, viewModel: HomeViewModel = koinViewModel()) {

    val pokemons by viewModel.state.collectAsState()

    LazyVerticalGrid(
        modifier = Modifier.padding(paddingValues),
        columns = GridCells.Fixed(count = 2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(pokemons) { index, item ->
            val isEven = index % 2 == 0
            CardPokemon(item, isEven)
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
                    modifier = Modifier.size(50.dp),
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png",
                    contentDescription = null
                )
            } else {
                AsyncImage(
                    modifier = Modifier.size(50.dp),
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png",
                    contentDescription = null
                )
                Text(text = pokemon.name)
            }
        }
    }
}