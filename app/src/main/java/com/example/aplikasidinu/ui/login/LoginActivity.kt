package com.example.aplikasidinu.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.aplikasidinu.R
import com.example.aplikasidinu.databinding.ActivityLoginBinding
import com.example.aplikasidinu.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import com.example.aplikasidinu.MainActivity
import kotlinx.coroutines.launch
import androidx.activity.viewModels

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        //enableEdgeToEdge()
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }

        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        //}

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            // Casting view ke EditText terlebih dahulu
            val etUsername = binding.etUsername as android.widget.EditText
            val etPassword = binding.etPassword as android.widget.EditText

            // Sekarang ambil .text dari variabel yang sudah di-cast
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Isi Semua Data Bakers!!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(username, password)
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect { ui ->
                // Tampilkan atau sembunyikan ProgressBar berdasarkan status isLoading
                binding.progressBar.visibility = if (ui.isLoading) View.VISIBLE else View.GONE
                if (ui.success) {
                    Toast.makeText(this@LoginActivity, ui.message ?: "Berhasil Bakers!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else if (ui.message != null) {
                    Toast.makeText(this@LoginActivity, ui.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}