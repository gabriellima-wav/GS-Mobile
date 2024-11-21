package com.example.gs_mobile.repository

import com.example.prospapp.api.ProspecoApiService

class ConquistaRepository(private val api: ProspecoApiService) {
    suspend fun getConquistaById(id: String) = api.getConquistaById(id)
    suspend fun updateConquista(id: String, data: Map<String, Any>) = api.updateConquista(id, data)
    suspend fun deleteConquista(id: String) = api.deleteConquista(id)
    suspend fun createConquista(data: Map<String, Any>) = api.createConquista(data)
    suspend fun getConquistasByUsuarioId(usuarioId: String) = api.getConquistasByUsuarioId(usuarioId)
}