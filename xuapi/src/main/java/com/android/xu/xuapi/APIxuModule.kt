package com.android.xu.xuapi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Named
import javax.inject.Singleton


@Module
class APIxuModule {

    @Singleton
    @Provides
    fun provideAPIXu(
        @Named("cache") cacheDir: File,
        interceptor: HttpLoggingInterceptor,
        @Named("xu-api-key") apiKey: String
    ): APIXu {
        return object : APIXu(apiKey) {
            override fun setOkHttpClientDefaults(builder: OkHttpClient.Builder) {
                super.setOkHttpClientDefaults(builder)
                builder.apply {
                    addInterceptor(interceptor)
                    cache(Cache(File(cacheDir, "xu_cache"), 10 * 1024 * 1024))
                }
            }
        }
    }


    @Provides
    internal fun getApiInterface(retroFit: Retrofit): WeatherService {
        return retroFit.create(WeatherService::class.java)
    }

    @Provides
    internal fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(" http://api.apixu.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


}