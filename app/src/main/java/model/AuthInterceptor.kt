package model

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $apiKey") // Si tu API requiere Bearer token
            // .addHeader("Authorization", apiKey) // Si tu API requiere solo la API Key
            .build()
        return chain.proceed(newRequest)
    }
}
