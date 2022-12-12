package com.example.ft_hangouts.networklayer

import com.example.ft_hangouts.repositories.Chat.Message
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "http://localhost:8080/"
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
    suspend fun getMessage() : List<Message>

}

//class MessageApiService {
//
//}