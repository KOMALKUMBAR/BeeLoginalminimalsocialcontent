package com.chat.beelogincompanyassigment.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chat.beelogincompanyassigment.data.repository.AuthRepository

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {

    val loginState = MutableLiveData<Boolean>()
    val registerState = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun login(email: String, password: String) {

        if (!repo.isUserRegistered()) {
            errorMessage.value = "Please create an account first"
            return
        }

        if (email.isEmpty() || password.isEmpty()) {
            errorMessage.value = "Please fill all fields"
            return
        }

        val result = repo.login(email, password)

        if (result) loginState.value = true
        else errorMessage.value = "Invalid credentials"
    }

    fun register(name: String, email: String, password: String) {

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errorMessage.value = "All fields required"
            return
        }

        val result = repo.register(name, email, password)

        if (result) registerState.value = true
        else errorMessage.value = "Registration failed"
    }
}