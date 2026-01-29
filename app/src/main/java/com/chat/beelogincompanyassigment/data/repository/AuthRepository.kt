package com.chat.beelogincompanyassigment.data.repository

import android.content.SharedPreferences

class AuthRepository(private val prefs: SharedPreferences) {

    fun isUserRegistered(): Boolean {
        return prefs.contains("email") && prefs.contains("password")
    }

    fun register(name: String, email: String, password: String): Boolean {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) return false

        prefs.edit()
            .putString("name", name)
            .putString("email", email)
            .putString("password", password)
            .apply()

        return true
    }

    fun login(email: String, password: String): Boolean {
        val savedEmail = prefs.getString("email", null)
        val savedPass = prefs.getString("password", null)

        return email == savedEmail && password == savedPass
    }
}