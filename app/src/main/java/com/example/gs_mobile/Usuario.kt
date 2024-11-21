data class Usuario(
    val nome: String,
    val sobrenome: String,
    val email: String,
    val endereco: String,
    val id: String

) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "nome" to nome,
            "sobrenome" to sobrenome,
            "email" to email,
            "endereco" to endereco,
            "id" to id
        )
    }
}
