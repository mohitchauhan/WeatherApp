
package com.android.xu.core.data.entities

sealed class Result<T> {
    open fun get(): T? = null
}

data class Success<T>(val data: T, val responseModified: Boolean = true) : Result<T>() {
    override fun get(): T = data
}

data class ErrorResult<T>(val exception: Exception? = null, val message: String? = null) : Result<T>()