package com.furmannsoft.maximatech.model

data class ShoesState(
    val isLoading: Boolean = true,
    val shoes: List<Shoes> = emptyList(),
    val filteredShoes: List<Shoes> = emptyList(),
    val searchQuery: String = "",
    val selectedFilter: String = "Todos"
)