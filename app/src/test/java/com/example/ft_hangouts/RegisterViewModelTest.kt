package com.example.ft_hangouts

import android.app.Application
import org.junit.Assert.*
import org.junit.Test


internal class RegisterViewModelTest {
    private val application  = Application()
    private val RegisterViewModel = RegisterViewModel(application = application)

    @Test
    fun testCheckEmailCorrect() {
        val email = "Thimo@gmail.com"
        assertEquals(true, RegisterViewModel.isValidEmail(email))
    }

    @Test
    fun testCheckEmailInCorrect() {
        val email = ""
        assertEquals(true, RegisterViewModel.isValidEmail(email))
    }

    @Test
    fun testCheckPasswordCorrect() {
        val password = "Hallo123456!"
        assertEquals(true, RegisterViewModel.isValidPassword(password))
    }

    @Test
    fun testCheckPasswordIncorrect() {
        val password = "hallo"
        assertEquals(true, RegisterViewModel.isValidPassword(password))
    }

}