package com.example.gs_mobile.repository

import com.example.prospapp.api.ProspecoApiService

class UsuarioRepository(private val api: ProspecoApiService) {
    suspend fun getUsuarioById(id: String) = api.getUsuarioById(id)
    suspend fun updateUsuario(id: String, data: Map<String, Any>) = api.updateUsuario(id, data)
    suspend fun deleteUsuario(id: String) = api.deleteUsuario(id)
    suspend fun getUsuarios() = api.getUsuarios()
    suspend fun createUsuario(data: Map<String, Any>) = api.createUsuario(data)
}
