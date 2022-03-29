package com.example.baubap.feature.login.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.baubap.R
import com.example.baubap.core.exception.Failure
import com.example.baubap.core.exception.Failure.*
import com.example.baubap.databinding.ActivityLoginBinding
import com.example.baubap.feature.login.data.model.LoggedInUserView
import com.example.baubap.feature.login.presentation.viewmodel.LoginViewModel
import com.example.baubap.feature.message.presentation.view.MessageDialogFragment
import dagger.hilt.android.AndroidEntryPoint

private lateinit var binding: ActivityLoginBinding

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        clickListenerViews()

        subscribeUI()
    }

    private fun clickListenerViews() {
        binding.btnLogin.setOnClickListener {
            loginViewModel.login(
                binding.etEmail.text.toString(), binding.etPassword.text.toString()
            )
        }
    }

    private fun subscribeUI() {
        loginViewModel.loggedInUserView.observe(this) {
            handleSignInSuccess(it)
        }

        loginViewModel.failure.observe(this) {
            handleFailure(it)
        }
    }

    private fun handleSignInSuccess(loggedInUserView: LoggedInUserView) {
        MessageDialogFragment.show(
            supportFragmentManager,
            getString(R.string.login_activity_title_dialog),
            loggedInUserView.displayName
        )
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is InvalidEmail -> {
                binding.etEmail.error = getString(failure.message)
            }
            is InvalidPassword -> {
                binding.etPassword.error = getString(failure.message)
            }
            is UnAuthorization -> {
                Toast.makeText(this@LoginActivity, "Invalid User or Password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
