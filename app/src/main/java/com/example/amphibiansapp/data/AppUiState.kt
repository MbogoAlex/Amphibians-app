package com.example.amphibiansapp.data

import com.example.amphibiansapp.model.AmphibianDetails

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<AmphibianDetails>): AmphibiansUiState
    object Loading: AmphibiansUiState
    object Error: AmphibiansUiState
}

data class AppUiState(
    val amphibiansUiState: AmphibiansUiState = AmphibiansUiState.Loading
)