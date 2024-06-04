package model

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("data/v1/estimate")
    fun calcularEmisiones(@Body requestBody: RequestBody): Call<EmissionFactorResponse>
}
