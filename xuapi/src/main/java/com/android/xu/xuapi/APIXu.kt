package com.android.xu.xuapi

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

open class APIXu(private val apiKey: String) {


    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    /**
     * Creates a [Retrofit.Builder] that sets the base URL, adds a Gson converter and sets [.okHttpClient]
     * as its client.
     *
     * @see .okHttpClient
     */
    protected fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(apiKey)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
    }


    /**
     * Returns the default OkHttp client instance. It is strongly recommended to override this and use your app
     * instance.
     *
     * @see .setOkHttpClientDefaults
     */
    @Synchronized
    protected fun okHttpClient(): OkHttpClient {
        if (okHttpClient == null) {
            val builder = OkHttpClient.Builder()
            setOkHttpClientDefaults(builder)
            okHttpClient = builder.build()
        }
        return okHttpClient as OkHttpClient
    }


    /**
     * Adds a network interceptor to add version and auth headers and a regular interceptor to log requests.
     */
    protected open fun setOkHttpClientDefaults(builder: OkHttpClient.Builder) {
    }

    /**
     * Return the current [Retrofit] instance. If none exists (first call, auth changed), builds a new one.
     *
     * When building, sets the base url and a custom client with an [Interceptor] which supplies authentication
     * data.
     */
    protected fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = retrofitBuilder().build()
        }
        return retrofit!!
    }

    fun weatherService(): WeatherService {
        return getRetrofit().create(WeatherService::class.java)
    }

}