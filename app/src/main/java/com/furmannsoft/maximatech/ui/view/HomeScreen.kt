package com.furmannsoft.maximatech.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.furmannsoft.maximatech.R
import com.furmannsoft.maximatech.model.Shoes
import com.furmannsoft.maximatech.model.ShoesIntent
import com.furmannsoft.maximatech.model.ShoesState
import com.furmannsoft.maximatech.ui.components.ItemComponent
import com.furmannsoft.maximatech.ui.theme.Orange

@Composable
fun HomeScreen(
    state: ShoesState,
    onIntent: (ShoesIntent) -> Unit,
    navController: NavController
) {
    val filters = listOf("Todos", "Tênis", "Botas", "Chuteiras", "Sapatênis")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
    ) {
        Text(
            text = "Olá, Luiz",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .padding(top = 20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )

        SearchBar(
            query = state.searchQuery,
            onQueryChanged = {
                onIntent(ShoesIntent.SearchShoes(it))
            },
            onSearchClicked = {
            }
        )

        FilterChips(
            filters = filters,
            selectedFilter = state.selectedFilter,
            onFilterSelected = {
                onIntent(ShoesIntent.SelectFilter(it))
            }
        )
        if (state.filteredShoes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Nada para exibir em ${state.selectedFilter}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.filteredShoes.size) { index ->
                    val shoe = state.filteredShoes[index]
                    ItemComponent(shoe, modifier = Modifier, navController)
                }
            }
        }

    }
}

@Composable
fun SearchBar(
    query: String = "",
    onQueryChanged: (String) -> Unit = {},
    onSearchClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChanged,
            placeholder = {
                Text(
                    "Pesquisar",
                    color = Color.LightGray
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            shape = RoundedCornerShape(20.dp),
            maxLines = 1,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
            )
        )

        Box(
            modifier = Modifier
                .size(53.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Orange)
                .clickable(onClick = onSearchClicked),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Pesquisar",
                tint = Color.White
            )
        }
    }
}

@Composable
fun FilterChips(
    filters: List<String>,
    selectedFilter: String?,
    onFilterSelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            val isSelected = filter == selectedFilter
            val backgroundColor = if (isSelected) Orange else Color.White
            val contentColor = if (isSelected) Color.White else Color.Black
            val borderColor = if (isSelected) Orange else Color.LightGray

            Card(
                modifier = Modifier
                    .height(30.dp)
                    .clickable { onFilterSelected(filter) }
                    .border(1.dp, borderColor, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = backgroundColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = filter,
                        color = if (isSelected) Color.White else Color.LightGray,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val mockShoes = listOf(
        Shoes(
            imageUrl = R.drawable.shoes1,
            itemId = 1,
            product = "Tênis Esportivo",
            description = "Tênis leve e confortável para corridas.",
            favorite = true,
            price = 199.99,
            star = 4
        ),
        Shoes(
            imageUrl = R.drawable.shoes5,
            itemId = 2,
            product = "Bota de Couro",
            description = "Ideal para trilhas e aventuras.",
            favorite = false,
            price = 299.99,
            star = 5
        ),
        Shoes(
            imageUrl = R.drawable.shoes1,
            itemId = 3,
            product = "Tênis Esportivo",
            description = "Tênis leve e confortável para corridas.",
            favorite = true,
            price = 199.99,
            star = 4
        ),
        Shoes(
            imageUrl = R.drawable.shoes5,
            itemId = 4,
            product = "Bota de Couro",
            description = "Ideal para trilhas e aventuras.",
            favorite = false,
            price = 299.99,
            star = 5
        )
    )

    val state = ShoesState(
        searchQuery = "",
        isLoading = false,
        filteredShoes = mockShoes
    )

    val fakeNavController = rememberNavController()

    HomeScreen(
        state = state,
        onIntent = {},
        navController = fakeNavController
    )
}