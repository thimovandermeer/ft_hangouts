package com.example.ft_hangouts.networklayer

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object MessageApi {
    val retrofitService: MessageApiService by lazy {
        retrofit.create(MessageApiService::class.java)
    }
}
interface MessageApiService {
    @GET("messages")
    suspend fun getMessage() : String

}

//class MessageApiService {
//
//}