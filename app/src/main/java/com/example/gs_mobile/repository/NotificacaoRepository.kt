package com.example.gs_mobile.repository

import com.example.prospapp.api.ProspecoApiService

class NotificacaoRepository(private val api: ProspecoApiService) {

    // Obter notificação por ID
    suspend fun getNotificacaoById(id: String) = api.getNotificacaoById(id)

    // Atualizar status de leitura
    suspend fun markNotificacaoAsLida(id: String) = api.markNotificacaoAsLida(id)

    // Criar nova notificação
    suspend fun createNotificacao(data: Map<String, Any>) = api.createNotificacao(data)

    // Deletar notificação
    suspend fun deleteNotificacao(id: String) = api.deleteNotificacao(id)

    // Obter notificações de um usuário por ID
    suspend fun getNotificacoesByUsuarioId(usuarioId: String) = api.getNotificacoesByUsuarioId(usuarioId)

    // Obter notificações não lidas de um usuário
    suspend fun getNaoLidasNotificacoesByUsuarioId(usuarioId: String) =
        api.getNaoLidasNotificacoesByUsuarioId(usuarioId)
}
