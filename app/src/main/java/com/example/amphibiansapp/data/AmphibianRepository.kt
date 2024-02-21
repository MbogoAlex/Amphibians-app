package com.example.amphibiansapp.data

import com.example.amphibiansapp.model.AmphibianDetails
import com.example.amphibiansapp.network.AmphibianApiService

interface AmphibianRepository {
    suspend fun getAmphibians(): List<AmphibianDetails>
}

class NetworkAmphibianRepository(private val amphibianApiService: AmphibianApiService) : AmphibianRepository {
    override suspend fun getAmphibians(): List<AmphibianDetails> = amphibianApiService.getAmphibians()
}