package com.example.jasebook

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class cookiejar:CookieJar {
    private var cookies: List<Cookie> = ArrayList()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies = cookies
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies
    }
}