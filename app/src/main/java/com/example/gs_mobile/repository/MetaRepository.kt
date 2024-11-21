package com.example.gs_mobile.repository

import com.example.prospapp.api.ProspecoApiService

class MetaRepository(private val api: ProspecoApiService) {
    suspend fun getMetaById(id: String) = api.getMetaById(id)
    suspend fun updateMeta(id: String, data: Map<String, Any>) = api.updateMeta(id, data)
    suspend fun deleteMeta(id: String) = api.deleteMeta(id)
    suspend fun createMeta(data: Map<String, Any>) = api.createMeta(data)
    suspend fun setMetaAtingida(id: String) = api.setMetaAtingida(id)
    suspend fun getMetasByUsuarioId(usuarioId: String) = api.getMetasByUsuarioId(usuarioId)
}
