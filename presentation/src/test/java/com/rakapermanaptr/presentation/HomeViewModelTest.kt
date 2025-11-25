package com.rakapermanaptr.presentation

import com.rakapermanaptr.domain.home.entity.Pokemon
import com.rakapermanaptr.domain.home.entity.PokemonList
import com.rakapermanaptr.domain.home.usecase.GetPokemonListUseCase
import com.rakapermanaptr.presentation.home.HomeViewEvent
import com.rakapermanaptr.presentation.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var viewModel: HomeViewModel
    private val getPokemonListUseCase: GetPokemonListUseCase = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = HomeViewModel(getPokemonListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial event to load pokemonList and update state`() = runTest {
        // given
        val dummyResult = PokemonList(
            pokemonList = listOf(Pokemon(name = "name1", url = "url1"), Pokemon(name = "name2", url = "url2")),
            nextOffset = 20
        )
        coEvery { getPokemonListUseCase(any(), any()) } returns dummyResult

        // when
        viewModel.onEvent(HomeViewEvent.Initial)
        dispatcher.scheduler.advanceUntilIdle()

        // then
        assertEquals(false, viewModel.state.value.isLoading)
        assertEquals(2, viewModel.state.value.pokemonList.size)
        assertEquals(true, viewModel.state.value.hasNextPage)
    }
}