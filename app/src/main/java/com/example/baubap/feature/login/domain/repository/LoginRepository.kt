package com.example.baubap.feature.login.domain.repository

import com.example.baubap.core.exception.Failure
import com.example.baubap.core.exception.Failure.*
import com.example.baubap.core.functional.Either
import com.example.baubap.core.functional.Either.Left
import com.example.baubap.core.functional.Either.Right
import com.example.baubap.feature.login.data.model.LoggedInUser
import javax.inject.Inject

interface LoginRepository {

    fun login(email: String, password: String): Either<Failure, LoggedInUser>

    class LoginDataSource @Inject constructor() : LoginRepository {

        override fun login(email: String, password: String): Either<Failure, LoggedInUser> {
            return if (email == "ini@gmail.com" && password == "12345678") {
                val fakeUser = LoggedInUser("1111", "Inigo Flores")
                Right(fakeUser)
            } else Left(UnAuthorization)
        }
    }
}
