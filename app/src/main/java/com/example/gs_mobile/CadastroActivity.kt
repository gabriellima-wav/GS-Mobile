package com.example.gs_mobile

import Usuario
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.gs_mobile.api.ApiResponse
import com.example.prospapp.api.ProspecoApiService
import com.example.prospapp.api.RetrofitInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etNome: EditText
    private lateinit var etSobrenome: EditText
    private lateinit var etEndereco: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Configurar a cor da barra de status
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBackground)

        // Inicializar elementos de interface
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etNome = findViewById(R.id.etNome)
        etSobrenome = findViewById(R.id.etSobrenome)
        etEndereco = findViewById(R.id.etEndereco)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        // Configurar clique no botão de cadastro
        btnCadastrar.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // Validação de campos
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                etNome.text.isEmpty() || etSobrenome.text.isEmpty() || etEndereco.text.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "As senhas não coincidem. Tente novamente.", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(email, password)
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        saveUserToFirebase(userId, email)
                    }
                } else {
                    Toast.makeText(this, "Erro ao cadastrar: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun saveUserToFirebase(userId: String, email: String) {
        val nome = etNome.text.toString().trim()
        val sobrenome = etSobrenome.text.toString().trim()
        val endereco = etEndereco.text.toString().trim()

        val id = ""
        val usuario = Usuario(nome, sobrenome, email, endereco, id)
        val database = FirebaseDatabase.getInstance()
        val userRef = database.getReference("usuarios").child(userId)

        userRef.setValue(usuario).addOnCompleteListener { dbTask ->
            if (dbTask.isSuccessful) {
                sendUserToApi(usuario)
                navigateToHome()
            } else {
                Toast.makeText(this, "Erro ao salvar dados no Firebase.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun sendUserToApi(usuario: Usuario) {
        lifecycleScope.launch {
            try {
                // Incluindo o id do usuário (se necessário)
                val usuarioMap =
                    usuario.toMap().toMutableMap()  // Supondo que você tenha um método que converte o usuario em Map
                usuarioMap["id"] = usuario.id  // Adicionando o id, se necessário

                val apiService = RetrofitInstance.api
                val response = apiService.createUsuario(usuarioMap)  // Passando o Map para o método da API

                if (response.success) {
                    Toast.makeText(this@CadastroActivity, "Usuário registrado na API com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@CadastroActivity, "Erro ao registrar usuário na API.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@CadastroActivity, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToHome() {
        Toast.makeText(this, "Cadastro concluído!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
