package com.furmannsoft.maximatech.viewModel

import androidx.lifecycle.ViewModel
import com.furmannsoft.maximatech.model.ShoesIntent
import com.furmannsoft.maximatech.model.ShoesState
import com.furmannsoft.maximatech.repository.ShoesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShoesViewModel(
    private val repository: ShoesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ShoesState())
    val state: StateFlow<ShoesState> = _state.asStateFlow()

    init {
        onEvent(ShoesIntent.LoadShoes)
    }

    fun onEvent(event: ShoesIntent) {
        when (event) {
            is ShoesIntent.LoadShoes -> {
                val shoes = repository.shoesList
                _state.value = ShoesState(
                    isLoading = false,
                    shoes = shoes,
                    filteredShoes = shoes
                )
            }

            is ShoesIntent.SearchShoes -> {
                val filtered = if (event.query.isBlank()) {
                    _state.value.shoes
                } else {
                    _state.value.shoes.filter {
                        it.product.contains(event.query, ignoreCase = true)
                    }
                }

                _state.update {
                    it.copy(
                        searchQuery = event.query,
                        filteredShoes = filtered
                    )
                }
            }

            is ShoesIntent.SelectFilter -> {
                val filtered = if (event.category == "Todos") {
                    repository.shoesList
                } else {
                    repository.shoesList.filter {
                        it.product.contains(event.category, ignoreCase = true)
                    }
                }
                _state.value = _state.value.copy(shoes = filtered)
            }
        }
    }
}