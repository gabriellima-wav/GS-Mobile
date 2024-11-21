package com.example.gs_mobile

data class Notificacao(
    val id: String,         // ID único da notificação
    val usuarioId: String,  // ID do usuário associado à notificação
    val titulo: String,     // Título da notificação
    val mensagem: String,   // Mensagem detalhada
    val tipo: String?,      // Tipo da notificação (opcional, ex.: alerta, aviso)
    val lida: Boolean,      // Indica se a notificação foi lida
    val dataCriacao: String // Data de criação (ISO 8601)
)
