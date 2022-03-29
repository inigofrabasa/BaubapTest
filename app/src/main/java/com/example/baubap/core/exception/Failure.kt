package com.example.baubap.core.exception

import androidx.annotation.StringRes

sealed class Failure {
    object UnAuthorization: Failure()
    class InvalidEmail(@StringRes var message: Int) : Failure()
    class InvalidPassword(@StringRes var message: Int) : Failure()
}
