package com.android.app.weather.di

import com.android.xu.xuapi.APIXu
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class TestAPIxuModule {

    @Singleton
    @Provides
    fun provideAPIXu(): APIXu {
        return object : APIXu("TEST") {
            override fun setOkHttpClientDefaults(builder: OkHttpClient.Builder) {
                super.setOkHttpClientDefaults(builder)
            }

            override fun retrofitBuilder(): Retrofit.Builder {
                return Retrofit.Builder()
                    .baseUrl("http://localhost:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient())
            }



        }
    }


}