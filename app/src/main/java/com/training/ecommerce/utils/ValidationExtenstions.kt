package com.training.ecommerce.utils

import android.util.Patterns

// create extension function for email validation
fun String.isValidEmail(): Boolean {
   return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}


fun String.isValidPassword(): Boolean {
    val passwordPattern = Regex("^(?=.*[A-Z])(?=.*\\d).{6,}\$")
    return passwordPattern.matches(this)
}