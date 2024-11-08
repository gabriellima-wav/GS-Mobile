package com.example.gs_mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var btnResetPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        // Define a cor da barra de status para uma cor diferente do background
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBackground) // Defina a cor desejada aqui

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Inicializar os elementos da interface
        etEmail = findViewById(R.id.etEmail)
        btnResetPassword = findViewById(R.id.btnResetPassword)

        // Configura o botão de redefinição de senha
        btnResetPassword.setOnClickListener {
            val email = etEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, insira seu e-mail.", Toast.LENGTH_SHORT).show()
            } else {
                sendPasswordResetEmail(email)
            }
        }
    }

    // Função para enviar o e-mail de redefinição de senha
    private fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "E-mail de redefinição de senha enviado para $email.", Toast.LENGTH_LONG).show()
                    finish() // Fecha a atividade após o envio
                } else {
                    Toast.makeText(this, "Falha ao enviar e-mail. Verifique o e-mail e tente novamente.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
