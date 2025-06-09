package com.furmannsoft.maximatech.ui.components

import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.furmannsoft.maximatech.R
import com.furmannsoft.maximatech.model.Shoes

@Composable
fun ItemComponent(shoes: Shoes, modifier: Modifier = Modifier, navController: NavController) {

    Column(
        modifier = modifier
            .width(170.dp)
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clickable {
                    navController.navigate("shoesDetailsScreen/${shoes.itemId}")
                },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6)),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            AsyncImage(
                model = shoes.imageUrl,
                contentDescription = shoes.imageUrl.toString(),
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = shoes.product,
            style = MaterialTheme.typography.bodySmall,
            color = Color.LightGray,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "R$ %.2f".format(shoes.price),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemComponentPreview() {
    val mockShoes = Shoes(
        imageUrl = R.drawable.shoes2,
        itemId = 1,
        product = "Tênis Esportivo",
        description = "Tênis leve e confortável para corridas.",
        favorite = true,
        price = 199.99,
        star = 4
    )

    val navController = rememberNavController()

    MaterialTheme {
        ItemComponent(
            shoes = mockShoes,
            navController = navController
        )
    }
}