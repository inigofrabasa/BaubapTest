package com.example.baubap.core.utils

import android.util.Patterns
import com.example.baubap.core.extension.empty

fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun isValidPassword(password: String): Boolean = password.length >= 6

fun isFieldEmpty(text: String): Boolean = text == String.empty()
