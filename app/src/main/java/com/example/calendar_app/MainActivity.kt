package com.example.calendar_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.calendar_app.databinding.ActivityMainBinding
import com.example.calendar_app.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.text = "Login Book App"
//        binding.btnLogin.setOnClickListener(handleLogin())

        loginViewModel.loginResult.observe(this) {
            run {
                val intentActivity = Intent(this, HomeActivity::class.java)
//                intentActivity.putExtra("username", binding.txtUsername.text.toString())
                startActivity(intentActivity)
            }
        }
    }
}

//    private fun handleLogin(): View.OnClickListener {
//        return View.OnClickListener {

//            loginViewModel.login(
//                binding.txtUsername.text.toString(),
//                binding.txtPassword.text.toString()
//            )
//        }
//    }
//}