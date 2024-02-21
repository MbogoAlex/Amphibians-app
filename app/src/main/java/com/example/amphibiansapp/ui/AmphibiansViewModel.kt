package com.example.amphibiansapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibiansapp.AmphibiansDetailsApplication
import com.example.amphibiansapp.data.AmphibianRepository
import com.example.amphibiansapp.data.AmphibiansUiState
import com.example.amphibiansapp.data.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AmphibiansViewModel(val amphibianRepository: AmphibianRepository): ViewModel() {
    private val _uiState = MutableStateFlow(value = AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun loadAmphibians() {
        viewModelScope.launch {
            _uiState.value = try {
                val amphibians = amphibianRepository.getAmphibians()
                AppUiState(
                    amphibiansUiState = AmphibiansUiState.Success(amphibians)
                )
            } catch (e: IOException) {
                AppUiState(
                    amphibiansUiState = AmphibiansUiState.Error
                )
            } catch (e: HttpException) {
                AppUiState(
                    amphibiansUiState = AmphibiansUiState.Error
                )
            }
        }
    }

    init {
        loadAmphibians()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansDetailsApplication)
                val amphibianRepository = application.container.amphibianRepository
                AmphibiansViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }
}