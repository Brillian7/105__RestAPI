package com.example.consumerestapi.repository

import com.example.consumerestapi.service_api.KontakService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface  AppContainer{
    val kontakRepository: KontakRepository
}
class KontakContainer  : AppContainer {
    private val baseUrl = "https://kancadonor.net//donorq/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("applicatiton/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val kontakService: KontakService by lazy {
        retrofit.create(KontakService::class.java)
    }
    override val kontakRepository: KontakRepository by lazy {
        NetworkKontakRepository(kontakService)
    }

}