package com.example.gs_mobile.repository

import com.example.prospapp.api.ProspecoApiService

class RegistrationRepository(private val api: ProspecoApiService) {

    // Registrar um novo usuário
    suspend fun registerUser(registrationData: Map<String, Any>) =
        api.registerUser(registrationData)
}
