package com.example.gs_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Definir a cor da barra de status
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBackground)

        // Inicializar elementos de interface
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        // Configurar o clique no botão de cadastro
        btnCadastrar.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(email, password)
            }
        }
    }

    // Função para cadastrar o usuário
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Cadastro bem-sucedido
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()

                    // Limpar campos ou redirecionar para outra tela
                    etEmail.text.clear()
                    etPassword.text.clear()

                    // Redirecionar para a HomeActivity
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()  // Fecha a tela de cadastro
                } else {
                    // Falha no cadastro
                    Toast.makeText(this, "Erro ao cadastrar: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
