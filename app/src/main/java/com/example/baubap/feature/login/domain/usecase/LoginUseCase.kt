package com.example.baubap.feature.login.domain.usecase

import com.example.baubap.core.exception.Failure
import com.example.baubap.core.functional.Either
import com.example.baubap.feature.login.data.model.LoggedInUser
import com.example.baubap.feature.login.domain.repository.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    operator fun invoke(params: Params, onResult: (Either<Failure, LoggedInUser>) -> Unit = {}) {
        onResult(loginRepository.login(params.email, params.password))
    }

    data class Params(val email: String, val password: String)

}
