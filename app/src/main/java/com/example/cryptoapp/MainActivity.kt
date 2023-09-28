package com.example.cryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cryptoapp.presentation.navigation.Navigation
import com.example.cryptoapp.ui.theme.CryptoAppTheme
import com.example.cryptoapp.ui.theme.rememberWindowSizeClass
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = getViewModel()
            val windowSize = rememberWindowSizeClass()
            CryptoAppTheme(windowSize, darkTheme = viewModel.isDarkMode) {
                Navigation()
            }
        }
    }
}