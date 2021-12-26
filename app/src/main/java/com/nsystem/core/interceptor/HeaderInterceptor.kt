package com.nsystem.core.interceptor

import com.nsystem.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(KEY_USER_AGENT, BuildConfig.USER_AGENT)
            .addHeader(KEY_AUTHORIZATION, "token ${BuildConfig.OAUTH_TOKEN}")
            .build()

        return chain.proceed(request)
    }

    companion object {

        private const val KEY_USER_AGENT = "User-Agent"

        private const val KEY_AUTHORIZATION = "Authorization"
    }
}