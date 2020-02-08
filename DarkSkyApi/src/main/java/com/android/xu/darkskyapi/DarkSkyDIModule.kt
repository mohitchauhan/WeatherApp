package com.android.xu.darkskyapi
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
class DarkSkyDIModule {

    @Singleton
    @Provides
    fun provideAPIXu(
        @Named("cache") cacheDir: File,
        interceptor: HttpLoggingInterceptor,
        @Named("xu-api-key") apiKey: String
    ): DarkSkyAPI {
        return object : DarkSkyAPI(apiKey) {
            override fun setOkHttpClientDefaults(builder: OkHttpClient.Builder) {
                super.setOkHttpClientDefaults(builder)
                builder.apply {
                    addInterceptor(interceptor)
                    cache(Cache(File(cacheDir, "xu_cache"), 10 * 1024 * 1024))
                }
            }
        }
    }


}