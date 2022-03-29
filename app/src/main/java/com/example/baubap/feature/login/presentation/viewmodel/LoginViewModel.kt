package com.example.baubap.feature.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baubap.R
import com.example.baubap.core.exception.Failure.*
import com.example.baubap.core.platform.BaseViewModel
import com.example.baubap.core.utils.isFieldEmpty
import com.example.baubap.core.utils.isValidEmail
import com.example.baubap.core.utils.isValidPassword
import com.example.baubap.feature.login.data.model.LoggedInUser
import com.example.baubap.feature.login.data.model.LoggedInUserView
import com.example.baubap.feature.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    private val _loggedInUserView = MutableLiveData<LoggedInUserView>()
    val loggedInUserView: LiveData<LoggedInUserView> = _loggedInUserView

    fun login(email: String, password: String) {
        if (isValidation(email, password))
            loginUseCase(LoginUseCase.Params(email, password)) {
                it.fold(::handleFailure, ::handleLoginSuccess)
            }

    }

    private fun handleLoginSuccess(loggedInUser: LoggedInUser) {
        _loggedInUserView.value = LoggedInUserView(loggedInUser.displayName)
    }

    private fun isValidation(email: String, password: String): Boolean {
        var valid = true
        if (isFieldEmpty(email)) {
            valid = false
            handleFailure(InvalidEmail(R.string.error_field_required))
        } else if (!isValidEmail(email)) {
            valid = false
            handleFailure(InvalidEmail(R.string.error_syntax_invalid))
        }

        if (isFieldEmpty(password)) {
            valid = false
            handleFailure(InvalidPassword(R.string.error_field_required))
        } else if (!isValidPassword(password)) {
            valid = false
            handleFailure(InvalidPassword(R.string.error_password_weak))
        }

        return valid
    }
}
