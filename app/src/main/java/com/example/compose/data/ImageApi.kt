package com.example.compose.data

import retrofit2.http.GET

interface ImageApi {
    @GET("http://127.0.0.1:8125/")
    suspend fun getRandomImage(): Image

    companion object{
        const val BASE_URL = "http://127.0.0.1:8125/"
    }
}