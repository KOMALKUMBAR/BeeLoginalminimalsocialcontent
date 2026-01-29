package com.chat.beelogincompanyassigment.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chat.beelogincompanyassigment.data.repository.AuthRepository
import com.chat.beelogincompanyassigment.databinding.ActivitySignupBinding
import com.chat.beelogincompanyassigment.ui.viewmodel.AuthViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var vm: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = AuthViewModel(AuthRepository(getSharedPreferences("auth", MODE_PRIVATE)))

        binding.btnSignUp.setOnClickListener {
            val name = binding.etFullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            vm.register(name, email, pass)
        }

        vm.registerState.observe(this) {
            if (it) {
                Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        vm.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
    }
}