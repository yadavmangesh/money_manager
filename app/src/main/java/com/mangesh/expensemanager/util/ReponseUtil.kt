package com.mangesh.expensemanager.util

sealed class Result<out T : Any?> {

    data class Success<out T : Any>(val data: T) : Result<T>()

    data class Error(
        val error: Throwable,
        val message: String? = null,
    ) : Result<Nothing>()
}

fun genericErrorResponse() = Result.Error(Exception("Something went Wrong"))