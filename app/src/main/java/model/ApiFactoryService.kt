package model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactoryService {
    private const val BASE_URL = "https://api.climatiq.io/"
    private const val API_KEY = "ZRYJ5V7HSXMRFWQDYQ2KNGQPA95M" // Reemplaza con tu API Key

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(API_KEY))
        .build()

    val service: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}




