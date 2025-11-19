package com.rakapermanaptr.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(paddingValues: PaddingValues, viewModel: HomeViewModel = koinViewModel()) {
    Box(modifier = Modifier.padding(paddingValues)) {

    }
}