package com.wuc.network.interceptor

import com.wuc.framework.log.LogUtil
import com.wuc.network.constant.KEY_SAVE_USER_LOGIN
import com.wuc.network.constant.KEY_SAVE_USER_REGISTER
import com.wuc.network.constant.KEY_SET_COOKIE
import com.wuc.network.manager.CookiesManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author     wuchao
 * @date       2023/7/25 00:07
 * @desciption Cookies拦截器
 */
class CookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()

        val response = chain.proceed(newBuilder.build())
        val url = request.url().toString()
        val host = request.url().host()

        // set-cookie maybe has multi, login to save cookie
        if ((url.contains(KEY_SAVE_USER_LOGIN) || url.contains(KEY_SAVE_USER_REGISTER))
                && response.headers(KEY_SET_COOKIE).isNotEmpty()
        ) {
            val cookies = response.headers(KEY_SET_COOKIE)
            val cookiesStr = CookiesManager.encodeCookie(cookies)
            CookiesManager.saveCookies(cookiesStr)
            LogUtil.e("CookiesInterceptor:cookies:$cookies", tag = "smy")
        }
        return response
    }
}