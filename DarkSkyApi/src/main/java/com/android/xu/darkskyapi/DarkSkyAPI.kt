package com.android.xu.darkskyapi

import com.android.xu.darkskyapi.entities.DarkSkyResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class DarkSkyAPI(val apiKey: String) {


    companion object{
        const val HOST = "api.darksky.net"
        const val API_URL = "https://$HOST/";

    }

    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    /**
     * Creates a [Retrofit.Builder] that sets the base URL, adds a Gson converter and sets [.okHttpClient]
     * as its client.
     *
     * @see .okHttpClient
     */
    protected open fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(API_URL)
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
//        builder.addInterceptor(XuAPIInterceptor(this))

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

    fun weatherService(): DarkSkyService {
        return getRetrofit().create(DarkSkyService::class.java)
    }

    fun forecast( location: String): Response<DarkSkyResponse> {
        return weatherService().forecast(apiKey, location).execute()
    }


}