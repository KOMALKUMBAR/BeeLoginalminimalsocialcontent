package com.chat.beelogincompanyassigment.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chat.beelogincompanyassigment.data.repository.AuthRepository
import com.chat.beelogincompanyassigment.databinding.ActivityLoginBinding
import com.chat.beelogincompanyassigment.ui.main.HomeActivity
import com.chat.beelogincompanyassigment.ui.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var vm: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = AuthViewModel(AuthRepository(getSharedPreferences("auth", MODE_PRIVATE)))

        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString().trim()
            val pass = binding.etLoginPassword.text.toString().trim()
            vm.login(email, pass)
        }

        vm.loginState.observe(this) {
            if (it) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        vm.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        binding.tvCreate.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}