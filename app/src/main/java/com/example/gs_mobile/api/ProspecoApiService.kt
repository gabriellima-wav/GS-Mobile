package com.example.prospapp.api

import com.example.gs_mobile.api.ApiResponse
import retrofit2.http.*

interface ProspecoApiService {

    // Usuario Controller

    // Obter um usuário por ID
    @GET("api/usuarios/{id}")
    suspend fun getUsuarioById(@Path("id") userId: String): ApiResponse

    // Atualizar um usuário por ID
    @PUT("api/usuarios/{id}")
    suspend fun updateUsuario(
        @Path("id") userId: String,
        @Body userData: Map<String, Any>
    ): ApiResponse

    // Deletar um usuário por ID
    @DELETE("api/usuarios/{id}")
    suspend fun deleteUsuario(@Path("id") userId: String): ApiResponse

    // Obter a lista de usuários
    @GET("api/usuarios")
    suspend fun getUsuarios(): ApiResponse

    // Criar um novo usuário
    @POST("api/usuarios")
    suspend fun createUsuario(@Body userData: Map<String, Any>): ApiResponse

    // Registro Consumo Controller

    // Obter registro de consumo por ID
    @GET("api/registro-consumo/{id}")
    suspend fun getRegistroConsumoById(@Path("id") registroId: String): ApiResponse

    // Atualizar registro de consumo por ID
    @PUT("api/registro-consumo/{id}")
    suspend fun updateRegistroConsumo(
        @Path("id") registroId: String,
        @Body registroData: Map<String, Any>
    ): ApiResponse

    // Deletar registro de consumo por ID
    @DELETE("api/registro-consumo/{id}")
    suspend fun deleteRegistroConsumo(@Path("id") registroId: String): ApiResponse

    // Criar um novo registro de consumo
    @POST("api/registro-consumo")
    suspend fun createRegistroConsumo(@Body registroData: Map<String, Any>): ApiResponse

    // Obter registros de consumo por aparelho ID
    @GET("api/registro-consumo/aparelho/{aparelhoId}")
    suspend fun getRegistrosConsumoByAparelhoId(@Path("aparelhoId") aparelhoId: String): ApiResponse

    // Meta Controller

    // Obter meta por ID
    @GET("api/metas/{id}")
    suspend fun getMetaById(@Path("id") metaId: String): ApiResponse

    // Atualizar meta por ID
    @PUT("api/metas/{id}")
    suspend fun updateMeta(
        @Path("id") metaId: String,
        @Body metaData: Map<String, Any>
    ): ApiResponse

    // Deletar meta por ID
    @DELETE("api/metas/{id}")
    suspend fun deleteMeta(@Path("id") metaId: String): ApiResponse

    // Criar uma nova meta
    @POST("api/metas")
    suspend fun createMeta(@Body metaData: Map<String, Any>): ApiResponse

    // Atualizar status de meta para atingida
    @PATCH("api/metas/{id}/atingida")
    suspend fun setMetaAtingida(@Path("id") metaId: String): ApiResponse

    // Obter metas por ID do usuário
    @GET("api/metas/usuario/{usuarioId}")
    suspend fun getMetasByUsuarioId(@Path("usuarioId") usuarioId: String): ApiResponse

    // Conquista Controller

    // Obter conquista por ID
    @GET("api/conquistas/{id}")
    suspend fun getConquistaById(@Path("id") conquistaId: String): ApiResponse

    // Atualizar conquista por ID
    @PUT("api/conquistas/{id}")
    suspend fun updateConquista(
        @Path("id") conquistaId: String,
        @Body conquistaData: Map<String, Any>
    ): ApiResponse

    // Deletar conquista por ID
    @DELETE("api/conquistas/{id}")
    suspend fun deleteConquista(@Path("id") conquistaId: String): ApiResponse

    // Criar uma nova conquista
    @POST("api/conquistas")
    suspend fun createConquista(@Body conquistaData: Map<String, Any>): ApiResponse

    // Obter conquistas por ID do usuário
    @GET("api/conquistas/usuario/{usuarioId}")
    suspend fun getConquistasByUsuarioId(@Path("usuarioId") usuarioId: String): ApiResponse

    // Bandeira Tarifária Controller

    // Obter bandeira tarifária por ID
    @GET("api/bandeiras-tarifarias/{id}")
    suspend fun getBandeiraTarifariaById(@Path("id") bandeiraId: String): ApiResponse

    // Atualizar bandeira tarifária por ID
    @PUT("api/bandeiras-tarifarias/{id}")
    suspend fun updateBandeiraTarifaria(
        @Path("id") bandeiraId: String,
        @Body bandeiraData: Map<String, Any>
    ): ApiResponse

    // Deletar bandeira tarifária por ID
    @DELETE("api/bandeiras-tarifarias/{id}")
    suspend fun deleteBandeiraTarifaria(@Path("id") bandeiraId: String): ApiResponse

    // Obter todas as bandeiras tarifárias
    @GET("api/bandeiras-tarifarias")
    suspend fun getBandeirasTarifarias(): ApiResponse

    // Criar uma nova bandeira tarifária
    @POST("api/bandeiras-tarifarias")
    suspend fun createBandeiraTarifaria(@Body bandeiraData: Map<String, Any>): ApiResponse

    // Aparelho Controller

    // Obter aparelho por ID
    @GET("api/aparelhos/{id}")
    suspend fun getAparelhoById(@Path("id") aparelhoId: String): ApiResponse

    // Atualizar aparelho por ID
    @PUT("api/aparelhos/{id}")
    suspend fun updateAparelho(
        @Path("id") aparelhoId: String,
        @Body aparelhoData: Map<String, Any>
    ): ApiResponse

    // Deletar aparelho por ID
    @DELETE("api/aparelhos/{id}")
    suspend fun deleteAparelho(@Path("id") aparelhoId: String): ApiResponse

    // Criar um novo aparelho para um usuário
    @POST("api/aparelhos/usuario/{usuarioId}")
    suspend fun createAparelho(
        @Path("usuarioId") usuarioId: String,
        @Body aparelhoData: Map<String, Any>
    ): ApiResponse

    // Obter aparelhos por ID do usuário
    @GET("api/aparelhos/{usuarioId}/aparelhos")
    suspend fun getAparelhosByUsuarioId(@Path("usuarioId") usuarioId: String): ApiResponse

    @POST("/auth/register")
    suspend fun registerUser(@Body registrationData: Map<String, Any>): ApiResponse

    @POST("/auth/login")
    suspend fun loginUser(@Body loginData: Map<String, Any>): ApiResponse

    // Recomendacao Controller

    // Criar uma nova recomendação
    @POST("/api/recomendacoes")
    suspend fun createRecomendacao(@Body recomendacaoData: Map<String, Any>): ApiResponse

    // Obter recomendações de um usuário
    @GET("/api/recomendacoes/usuarios/{usuarioId}")
    suspend fun getRecomendacoesByUsuarioId(@Path("usuarioId") usuarioId: String): ApiResponse

    // Criar uma nova notificação
    @POST("/api/notificacoes")
    suspend fun createNotificacao(@Body notificacaoData: Map<String, Any>): ApiResponse

    // Atualizar o status de uma notificação para "lida"
    @PATCH("/api/notificacoes/{id}/lida")
    suspend fun markNotificacaoAsLida(@Path("id") notificacaoId: String): ApiResponse

    // Obter uma notificação por ID
    @GET("/api/notificacoes/{id}")
    suspend fun getNotificacaoById(@Path("id") notificacaoId: String): ApiResponse

    // Deletar uma notificação por ID
    @DELETE("/api/notificacoes/{id}")
    suspend fun deleteNotificacao(@Path("id") notificacaoId: String): ApiResponse

    // Obter notificações de um usuário por ID
    @GET("/api/notificacoes/usuario/{usuarioId}")
    suspend fun getNotificacoesByUsuarioId(@Path("usuarioId") usuarioId: String): ApiResponse

    // Obter notificações não lidas de um usuário por ID
    @GET("/api/notificacoes/usuario/{usuarioId}/nao-lidas")
    suspend fun getNaoLidasNotificacoesByUsuarioId(@Path("usuarioId") usuarioId: String): ApiResponse
}
