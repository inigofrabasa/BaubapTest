package com.example.baubap.feature.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.baubap.R
import com.example.baubap.feature.login.presentation.view.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Test
    fun buttonLogin_click_showDialog() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.et_email)).perform(typeText("ini@gmail.com"))
        onView(withId(R.id.et_password)).perform(typeText("12345678"))
        onView(withId(R.id.btn_login)).perform(click())

        activityScenario.close()
    }
}