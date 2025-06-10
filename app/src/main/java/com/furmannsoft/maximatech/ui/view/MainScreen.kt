package com.furmannsoft.maximatech.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.furmannsoft.maximatech.model.NavigationItems
import com.furmannsoft.maximatech.viewModel.ShoesViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    shoesViewModel: ShoesViewModel
) {
    val navItemList = listOf(
        NavigationItems("Home", Icons.Default.Home),
        NavigationItems("Carrinho", Icons.Default.ShoppingCart),
        NavigationItems("Perfil", Icons.Default.Person),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        topBar = {
            // Sem topo por enquanto
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White  // fundo branco da NavigationBar
            ) {
                navItemList.forEachIndexed { index, navigationItems ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                imageVector = navigationItems.icon,
                                contentDescription = "Icon",
                                tint = if (selectedIndex == index) Color(0xFFFF9800) /* Laranja */ else Color.Gray
                            )
                        },
                        label = {
                            Text(
                                text = navigationItems.label,
                                color = if (selectedIndex == index) Color(0xFFFF9800) else Color.Gray
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFFF9800),
                            unselectedIconColor = Color.Gray,
                            selectedTextColor = Color(0xFFFF9800),
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent // Remove background color do item selecionado
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            ContentScreen(modifier = Modifier.fillMaxSize(), selectedIndex, shoesViewModel, navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    shoesViewModel: ShoesViewModel,
    navController: NavController
) {
    when(selectedIndex) {
        0-> HomeScreen(
            state = shoesViewModel.state.collectAsState().value,
            onIntent = shoesViewModel::onEvent,
            navController
        )
        1-> CartScreen(modifier)
        2-> ProlifeScreen(modifier)
    }
}