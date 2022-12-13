package com.example.ft_hangouts.networklayer

import com.example.ft_hangouts.repositories.Chat.Message
import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL =
    "http://10.0.2.2:8080/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
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

    @GET("/{id}")
    suspend fun getChannelMessages(@Path("id") channelID: String) : List<Message>

    @POST("/{id}")
    suspend fun sendMessage(@Path("id") channelID: String, message: Message)
}

