package model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactoryService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.climatiq.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiService::class.java)

}