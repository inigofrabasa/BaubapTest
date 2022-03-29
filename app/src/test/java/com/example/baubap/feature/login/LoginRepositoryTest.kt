package com.example.baubap.feature.login

import com.example.baubap.UnitTest
import com.example.baubap.core.exception.Failure
import com.example.baubap.core.functional.Either
import com.example.baubap.core.functional.Either.Right
import com.example.baubap.feature.login.data.model.LoggedInUser
import com.example.baubap.feature.login.domain.repository.LoginRepository
import com.example.baubap.feature.login.domain.repository.LoginRepository.LoginDataSource
import io.mockk.impl.annotations.MockK
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Before
import org.junit.Test

class LoginRepositoryTest : UnitTest() {
    @MockK
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        loginRepository = LoginDataSource()
    }

    @Test
    fun `login repo should return LoggedInUser when successful login`() {

        val login = loginRepository.login("ini@gmail.com", "12345678")

        login shouldBeEqualTo Right(LoggedInUser("1111", "Inigo Flores"))
    }

    @Test
    fun `login repo should return UnAuthorization when  no successful login`() {

        val login = loginRepository.login("ini@gmail.com", "123456")

        login shouldBeInstanceOf Either::class.java
        login.isLeft shouldBeEqualTo true
        login.fold(
            { failure -> failure shouldBeInstanceOf Failure.UnAuthorization::class.java },
            {})
    }
}
