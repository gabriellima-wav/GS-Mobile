package com.example.gs_mobile.repository

import com.example.prospapp.api.ProspecoApiService

class RecomendacaoRepository(private val api: ProspecoApiService) {

    suspend fun createRecomendacao(recomendacaoData: Map<String, Any>) =
        api.createRecomendacao(recomendacaoData)

    suspend fun getRecomendacoesByUsuarioId(usuarioId: String) =
        api.getRecomendacoesByUsuarioId(usuarioId)
}
