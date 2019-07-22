package com.android.xu.data.mappers

interface Mapper<F, T> {
    suspend fun map(from: F): T
}

