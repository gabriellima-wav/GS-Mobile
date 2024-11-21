package com.example.gs_mobile.repository

import com.example.prospapp.api.ProspecoApiService

class AparelhoRepository(private val api: ProspecoApiService) {

    // Obter um aparelho por ID
    suspend fun getAparelhoById(id: String) = api.getAparelhoById(id)
    // Atualizar um aparelho por ID
    suspend fun updateAparelho(id: String, data: Map<String, Any>) = api.updateAparelho(id, data)
    // Deletar um aparelho por ID
    suspend fun deleteAparelho(id: String) = api.deleteAparelho(id)
    // Criar um novo aparelho para um usuário
    suspend fun createAparelho(usuarioId: String, data: Map<String, Any>) =
        api.createAparelho(usuarioId, data)
    // Obter aparelhos por ID do usuário
    suspend fun getAparelhosByUsuarioId(usuarioId: String) = api.getAparelhosByUsuarioId(usuarioId)
}
