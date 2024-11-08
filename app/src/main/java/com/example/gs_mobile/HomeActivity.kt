package com.example.gs_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.gs_mobile.LoginActivity
import com.example.gs_mobile.R
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btnLogout: Button
    private lateinit var tvWelcomeMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inicializando FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Altera a cor da barra de status
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBackground)

        // Inicializando o botão de logout e a TextView
        btnLogout = findViewById(R.id.btnLogout)
        tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage)

        // Exibe a mensagem de boas-vindas
        displayWelcomeMessage()

        // Configura o clique do botão de logout
        btnLogout.setOnClickListener {
            logout()
        }
    }

    // Função para exibir a mensagem de boas-vindas
    private fun displayWelcomeMessage() {
        val user = auth.currentUser
        if (user != null) {
            val welcomeText = "Bem-vindo, ${user.displayName ?: user.email}!"
            tvWelcomeMessage.text = welcomeText
        } else {
            // Caso o usuário não esteja autenticado, redireciona para LoginActivity
            Toast.makeText(this, "Usuário não autenticado. Redirecionando para login...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()  // Finaliza a atividade atual
        }
    }

    // Função para realizar logout
    private fun logout() {
        auth.signOut()
        Toast.makeText(this, "Logout realizado com sucesso!", Toast.LENGTH_SHORT).show()
        // Redireciona para a tela de login após logout
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Fecha a tela Home
    }
}
