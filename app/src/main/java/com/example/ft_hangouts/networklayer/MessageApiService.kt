package com.example.ft_hangouts.networklayer

import android.util.Log
import com.example.ft_hangouts.repositories.Chat.Chats
import com.example.ft_hangouts.repositories.Chat.Message
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

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
    suspend fun sendMessage(@Path("id") channelID: String, @Body message: Message)
}

object ChannelApi {
    val retrofitService: ChannelApiService by lazy {
        Log.d("CHANNEL API", "Test")
        retrofit.create(ChannelApiService::class.java)
    }
}
interface ChannelApiService {
    @GET("channels")
    suspend fun getChannels() : List<Chats>

    @GET("channels/{id}")
    suspend fun getChannel(@Path("id") channelID: String) : Chats

    @POST("channels")
    suspend fun CreateChannel(@Body newChatName: Chats)
}

