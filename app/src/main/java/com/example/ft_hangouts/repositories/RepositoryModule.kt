package com.example.ft_hangouts.repositories

import com.example.ft_hangouts.repositories.Chat.ChannelRepository
import com.example.ft_hangouts.repositories.Chat.ChannelRepositoryImpl
import com.example.ft_hangouts.repositories.User.UserRepository
import com.example.ft_hangouts.repositories.User.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesUserRepository(impl: UserRepositoryImpl) : UserRepository

    @Binds
    abstract fun providesChannelRepository(impl: ChannelRepositoryImpl) : ChannelRepository

}