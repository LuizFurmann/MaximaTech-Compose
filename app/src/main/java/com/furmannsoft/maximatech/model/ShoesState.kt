package com.furmannsoft.maximatech.model

data class ShoesState(
    val isLoading: Boolean = false,
    val shoes: List<Shoes> = emptyList()
)