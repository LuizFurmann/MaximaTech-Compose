package com.furmannsoft.maximatech.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.furmannsoft.maximatech.model.Shoes

@Composable
fun ItemComponent(shoes: Shoes, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(160.dp)
            .padding(8.dp)
    ) {
        // Card com imagem
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            AsyncImage(
                model = shoes.imageUrl,
                contentDescription = shoes.imageUrl.toString(),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nome do produto
        Text(
            text = shoes.product,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Preço do produto
        Text(
            text = shoes.price.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
@Preview
fun  ItemCmponentPreview() {

    val sampleShoes = Shoes(
        imageUrl = 4,
        itemId = 1,
        product = "Fone Bluetooth",
        description = "Fone com cancelamento de ruído",
        favorite = true,
        price = 149.99,
        star = 4
    )

    ItemComponent(shoes = sampleShoes)

}