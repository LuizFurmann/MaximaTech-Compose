package com.furmannsoft.maximatech

import com.furmannsoft.maximatech.model.Shoes
import com.furmannsoft.maximatech.model.ShoesIntent
import com.furmannsoft.maximatech.model.ShoesState
import com.furmannsoft.maximatech.repository.ShoesRepository
import com.furmannsoft.maximatech.viewModel.ShoesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class ExampleUnitTest {
    private val mockRepository: ShoesRepository = mock()

    private lateinit var viewModel: ShoesViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val dummyShoesList = listOf(
        Shoes(itemId = 1, product = "Tênis Esportivo A", description = "...", price = 100.0, imageUrl = 0, favorite = false, star = 4),
        Shoes(itemId = 2, product = "Bota de Couro B", description = "...", price = 200.0, imageUrl = 0, favorite = false, star = 3),
        Shoes(itemId = 3, product = "Chuteira de Campo C", description = "...", price = 150.0, imageUrl = 0, favorite = false, star = 5),
        Shoes(itemId = 4, product = "Sapatênis Conforto D", description = "...", price = 120.0, imageUrl = 0, favorite = false, star = 4),
        Shoes(itemId = 5, product = "Tênis Casual E", description = "...", price = 80.0, imageUrl = 0, favorite = false, star = 3)
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        whenever(mockRepository.shoesList).thenReturn(dummyShoesList)
        viewModel = ShoesViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `LoadShoes intent carrega os calcados na state inicial`() = runTest {
        val expectedState = ShoesState(
            isLoading = false,
            shoes = dummyShoesList,
            filteredShoes = dummyShoesList,
            searchQuery = "",
            selectedFilter = "Todos"
        )

        assertEquals(expectedState, viewModel.state.value)
    }

    @Test
    fun `SelectFilter intent filtrar calcado por categoria`() = runTest {
        viewModel.onEvent(ShoesIntent.SelectFilter("Chuteira"))
        advanceUntilIdle()

        val expectedFilteredList = listOf(dummyShoesList[2])
        assertEquals(expectedFilteredList, viewModel.state.value.filteredShoes)
        assertEquals("Chuteira", viewModel.state.value.selectedFilter)
    }

    @Test
    fun `getShoeById retorna o calcado correto pelo id`() {
        val shoe = viewModel.getShoeById(1)
        assertNotNull(shoe)
        assertEquals(dummyShoesList[0], shoe)
    }
}