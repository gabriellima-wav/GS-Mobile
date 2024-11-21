package com.example.gs_mobile.repository

import com.example.prospapp.api.ProspecoApiService

class RegistroConsumoRepository(private val api: ProspecoApiService) {
    suspend fun getRegistroConsumoById(id: String) = api.getRegistroConsumoById(id)
    suspend fun updateRegistroConsumo(id: String, data: Map<String, Any>) = api.updateRegistroConsumo(id, data)
    suspend fun deleteRegistroConsumo(id: String) = api.deleteRegistroConsumo(id)
    suspend fun createRegistroConsumo(data: Map<String, Any>) = api.createRegistroConsumo(data)
    suspend fun getRegistrosConsumoByAparelhoId(aparelhoId: String) = api.getRegistrosConsumoByAparelhoId(aparelhoId)
}
