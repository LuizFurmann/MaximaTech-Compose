package com.furmannsoft.maximatech.model

sealed class ShoesIntent {
    object LoadShoes : ShoesIntent()
    data class SearchShoes(val query: String) : ShoesIntent()
    data class SelectFilter(val category: String) : ShoesIntent()
}