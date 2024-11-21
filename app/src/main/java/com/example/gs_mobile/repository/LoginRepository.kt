package com.example.gs_mobile.repository

import com.example.prospapp.api.ProspecoApiService

class LoginRepository(private val api: ProspecoApiService) {

    suspend fun loginUser(loginData: Map<String, Any>) =
        api.loginUser(loginData)
}
