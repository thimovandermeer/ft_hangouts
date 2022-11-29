package com.example.ft_hangouts.ViewModels

import com.example.ft_hangouts.ViewModels.LoginViewModel.LoginState.*
import com.example.ft_hangouts.dao.UserDao
import com.example.ft_hangouts.repositories.User.UserRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import javax.inject.Inject


class LoginViewModelTest {
    private lateinit var userRepository: UserRepository
    private lateinit var LoginViewModel: LoginViewModel
    @Test
    fun testHandleInput() {
        runBlocking {
            userRepository.save( "Thimo", "Doei Doei", "thimo@gmail.com")
            userRepository.save("Shan", "Hallo Hallo", "Shan@gmail.com")
        }
        var result = LoginViewModel.handleLogin("Thimo", "Doei doei")
        assertEquals(REDIRECT, result)

        result = LoginViewModel.handleLogin("Thimo", "Doei")
        assertEquals(PASSWORDINCORRECT, result)

        result = LoginViewModel.handleLogin("Ingrid", "Doei")
        assertEquals(USERNOEXIST, result)
    }
}