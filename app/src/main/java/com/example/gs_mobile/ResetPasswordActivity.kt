package com.example.gs_mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var btnResetPassword: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        // Define a cor da barra de status
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBackground)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Inicializar os elementos da interface
        etEmail = findViewById(R.id.etEmail)
        btnResetPassword = findViewById(R.id.btnResetPassword)
        progressBar = findViewById(R.id.progressBar) // Suponha que tenha um ProgressBar no layout

        // Configura o botão de redefinição de senha
        btnResetPassword.setOnClickListener {
            val email = etEmail.text.toString().trim()

            if (email.isEmpty()) {
                showToast("Por favor, insira seu e-mail.")
            } else if (!isValidEmail(email)) {
                showToast("Por favor, insira um e-mail válido.")
            } else {
                sendPasswordResetEmail(email)
            }
        }
    }

    // Função para enviar o e-mail de redefinição de senha
    private fun sendPasswordResetEmail(email: String) {
        // Exibe o progresso enquanto o e-mail está sendo enviado
        progressBar.visibility = View.VISIBLE
        btnResetPassword.isEnabled = false

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                progressBar.visibility = View.GONE
                btnResetPassword.isEnabled = true

                if (task.isSuccessful) {
                    showToast("E-mail de redefinição de senha enviado para $email.")
                    finish() // Fecha a atividade após o envio
                } else {
                    // Captura erros específicos de falha ao enviar o e-mail
                    val errorMessage = task.exception?.message
                    when {
                        errorMessage?.contains("user not found") == true -> {
                            showToast("Nenhum usuário encontrado com esse e-mail.")
                        }
                        errorMessage?.contains("invalid email") == true -> {
                            showToast("E-mail inválido. Por favor, verifique o e-mail digitado.")
                        }
                        else -> {
                            showToast("Falha ao enviar e-mail. Tente novamente.")
                        }
                    }
                }
            }
    }

    // Função para validar o formato do e-mail
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Função auxiliar para exibir mensagens de Toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
