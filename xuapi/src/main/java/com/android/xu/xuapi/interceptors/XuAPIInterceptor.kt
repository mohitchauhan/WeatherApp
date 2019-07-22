package com.android.xu.xuapi.interceptors

import com.android.xu.xuapi.APIXu
import okhttp3.Interceptor
import okhttp3.Response

class XuAPIInterceptor(val apiXu: APIXu) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request();
        if (APIXu.HOST != request.url.host) {
            // do not intercept requests for other hosts
            // this allows the interceptor to be used on a shared okhttp client
            return chain.proceed(request)
        }

        // add (or replace) the API key query parameter
        val urlBuilder = request.url.newBuilder()
        urlBuilder.setEncodedQueryParameter(APIXu.PARAM_API_KEY, apiXu.apiKey)

        val builder = request.newBuilder()
        builder.url(urlBuilder.build())
       return chain.proceed(builder.build())
    }
}