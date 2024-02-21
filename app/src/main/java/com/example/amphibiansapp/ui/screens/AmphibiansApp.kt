package com.example.amphibiansapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibiansapp.ui.AmphibiansViewModel
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme

@Composable
fun AmphibiansApp(
    modifier: Modifier = Modifier
) {
    val viewModel: AmphibiansViewModel = viewModel(
        factory = AmphibiansViewModel.Factory
    )
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        appUiState = uiState,
        retryAction = { viewModel.loadAmphibians() }
    )
}

@Preview(showBackground = true)
@Composable
fun AmphibiansAppPreview(
    modifier: Modifier = Modifier
) {
    AmphibiansAppTheme {
        AmphibiansApp()
    }
}