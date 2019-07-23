package com.android.xu.core.extensions

import com.android.xu.core.data.entities.ErrorResult
import com.android.xu.core.data.entities.Result
import com.android.xu.core.data.entities.Success
import retrofit2.HttpException
import retrofit2.Response


fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw HttpException(this)
    return body()!!
}


fun <T> Response<T>.toException() = HttpException(this)


@Suppress("REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE")
suspend fun <T> Response<T>.toResultUnit(): Result<Unit> = toResult { Unit }

@Suppress("REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE")
suspend fun <T> Response<T>.toResult(): Result<T> = toResult { it }



@Suppress("REDUNDANT_INLINE_SUSPEND_FUNCTION_TYPE")
suspend fun <T, E> Response<T>.toResult(mapper: suspend (T) -> E): Result<E> {
    return try {
        if (isSuccessful) {
            Success(data = mapper(bodyOrThrow()), responseModified = false)
        } else {
            ErrorResult(toException())
        }
    } catch (e: Exception) {
        ErrorResult(e)
    }
}
