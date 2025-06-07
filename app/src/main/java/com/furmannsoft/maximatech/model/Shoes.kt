package com.furmannsoft.maximatech.model

data class Shoes(
    var imageUrl: Int,
    var itemId: Int,
    var product: String,
    var description: String,
    var favorite: Boolean,
    var price: Double,
    var star: Int
)
