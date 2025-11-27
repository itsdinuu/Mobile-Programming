package com.example.aplikasidinu.ui.register

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.aplikasidinu.R
import com.example.aplikasidinu.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue



@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //enableEdgeToEdge()
        setContentView(binding.root)

        @Suppress("DEPRECATION")

        window.statusBarColor = ContextCompat.getColor(this,R.color.white)

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

        binding.tvLogin.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val full_name = binding.etFullname.text.toString()
            val date_of_birth = binding.etDateOfBirth.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this@RegisterActivity, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.register(username, full_name, date_of_birth, password)
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect { ui ->
                if (ui.isLoading) {
                    // show loading
                    Toast.makeText(this@RegisterActivity, "Loading", Toast.LENGTH_SHORT).show()
                }

                ui.message?.let {
                    Toast.makeText(this@RegisterActivity, it, Toast.LENGTH_SHORT).show()
                }

                if (ui.success) {
                    // navigate ke home
                    Toast.makeText(this@RegisterActivity, "Suksess", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}