package com.furmannsoft.maximatech.repository

import com.furmannsoft.maximatech.R
import com.furmannsoft.maximatech.model.Shoes

class ShoesRepository {

    val shoesList = listOf(
        Shoes(R.drawable.shoes1, 1, "Tênis Esportivo", "Confortável e leve", true, 199.99, 4),
        Shoes(R.drawable.shoes2, 2, "Sapato bicudo", "Ideal para o dia a dia", false, 149.90, 5),
        Shoes(R.drawable.shoes6, 6, "Chuteira feminina", "Resistente e elegante", true, 299.00, 3),
        Shoes(R.drawable.shoes3, 3, "Sapato de veio", "Resistente e elegante", false, 299.00, 3),
        Shoes(R.drawable.shoes4, 4, "Tênis de volta para o futuro", "Resistente e elegante", true, 299.00, 3),
        Shoes(R.drawable.shoes5, 5, "Bota de Couro", "Resistente e elegante", false, 299.00, 3),
        Shoes(R.drawable.shoes7, 7, "Bota de Couro", "Resistente e elegante", false, 299.00, 3),
        Shoes(R.drawable.shoes8, 8, "Bota de Couro", "Resistente e elegante", false, 299.00, 3),
        Shoes(R.drawable.shoes9, 9, "Bota de Couro", "Resistente e elegante", true, 299.00, 3)
    )
}