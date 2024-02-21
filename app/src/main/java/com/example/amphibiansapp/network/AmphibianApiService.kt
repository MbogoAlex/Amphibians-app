package com.example.amphibiansapp.network

import com.example.amphibiansapp.model.AmphibianDetails
import retrofit2.http.GET


interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getAmphibians() : List<AmphibianDetails>
}