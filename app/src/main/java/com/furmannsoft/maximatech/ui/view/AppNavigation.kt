package com.furmannsoft.maximatech.ui.view

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.furmannsoft.maximatech.viewModel.ShoesViewModel
import org.koin.androidx.compose.getViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(modifier: Modifier = Modifier, context: Context) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            val viewModel: ShoesViewModel = getViewModel()
            MainScreen(
                modifier = modifier,
                navController = navController,
                shoesViewModel = viewModel
            )
        }

        composable("homeScreen") {
            val viewModel: ShoesViewModel = getViewModel()
            val state by viewModel.state.collectAsState()

            HomeScreen(
                state = state,
                onIntent = viewModel::onEvent
            )
        }

        composable("cartScreen") {
            CartScreen(modifier)
        }

        composable("profileScreen") {
            ProlifeScreen(modifier)
        }
    }
}