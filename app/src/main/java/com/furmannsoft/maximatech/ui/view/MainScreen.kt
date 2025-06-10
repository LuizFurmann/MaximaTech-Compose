package com.furmannsoft.maximatech.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.furmannsoft.maximatech.R
import com.furmannsoft.maximatech.model.NavIcon
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
        NavigationItems("Home", NavIcon.DrawableResIcon(R.drawable.home)),
        NavigationItems("Carrinho", NavIcon.DrawableResIcon(R.drawable.cart)),
        NavigationItems("Perfil", NavIcon.DrawableResIcon(R.drawable.profile)),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            Column {
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp
                )
            NavigationBar(
                containerColor = Color.White
            ) {
                navItemList.forEachIndexed { index, navigationItems ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            when (navigationItems.icon) {
                                is NavIcon.ImageVectorIcon -> {
                                    Icon(
                                        imageVector = (navigationItems.icon as NavIcon.ImageVectorIcon).imageVector,
                                        contentDescription = navigationItems.label, // Use o label para acessibilidade
                                        tint = if (selectedIndex == index) Color(0xFFFF9800) /* Laranja */ else Color.Gray,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                is NavIcon.DrawableResIcon -> {
                                    Icon(
                                        painter = painterResource(id = (navigationItems.icon as NavIcon.DrawableResIcon).drawableRes),
                                        contentDescription = navigationItems.label, // Use o label para acessibilidade
                                        tint = if (selectedIndex == index) Color(0xFFFF9800) /* Laranja */ else Color.Gray,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
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
                            indicatorColor = Color.Transparent
                        )
                    )
                }
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