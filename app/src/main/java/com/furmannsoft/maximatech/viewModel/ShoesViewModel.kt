package com.furmannsoft.maximatech.viewModel

import androidx.lifecycle.ViewModel
import com.furmannsoft.maximatech.model.ShoesEvent
import com.furmannsoft.maximatech.model.ShoesState
import com.furmannsoft.maximatech.repository.ShoesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShoesViewModel(
    private val repository: ShoesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ShoesState())
    val state: StateFlow<ShoesState> = _state.asStateFlow()

    init {
        onEvent(ShoesEvent.LoadShoes)
    }

    fun onEvent(event: ShoesEvent) {
        when (event) {
            is ShoesEvent.LoadShoes -> {
                _state.value = ShoesState(
                    isLoading = false,
                    shoes = repository.shoesList
                )
            }
        }
    }
}