package com.example.ft_hangouts.ViewModels

import androidx.lifecycle.ViewModel
import com.example.ft_hangouts.MainActivity
import com.example.ft_hangouts.User
import com.example.ft_hangouts.repositories.User.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val UserRepository: UserRepository
) : ViewModel() {
    enum class LoginState {USERNOEXIST, PASSWORDINCORRECT, REDIRECT, DONE, INPROGRESS}
    fun getLocalList(username : String) : User? {
        return UserRepository.searchLocalListUser(username)
    }
    fun handleLogin(username: String, password: String) : LoginState {
        val user = getLocalList(username)
        return if (user != null) {
            if (user.password == password) {
                MainActivity.username = username
                LoginState.REDIRECT
            } else {
                LoginState.PASSWORDINCORRECT
            }
        } else {
            LoginState.USERNOEXIST
        }
    }
}