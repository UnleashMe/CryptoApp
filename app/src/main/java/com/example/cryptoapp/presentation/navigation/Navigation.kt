package com.example.cryptoapp.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    viewModel: NavigationViewModel = getViewModel()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val screens = listOf(Screen.AssetInfo, Screen.AssetList, Screen.Settings)
    val currentScreen = screens.find { it.route == navBackStackEntry?.destination?.route }

    Scaffold(topBar = {
        Appbar(
            isPrimary = currentScreen == Screen.AssetList,
            title = if (currentScreen == Screen.AssetInfo) {
                viewModel.title
            } else currentScreen?.title ?: "",
            text = if (currentScreen == Screen.AssetInfo) {
                viewModel.abbreviation
            } else currentScreen?.text ?: "",
            navController
        )
    }) {
        NavGraph(navController = navController, paddingValues = it)
    }
}