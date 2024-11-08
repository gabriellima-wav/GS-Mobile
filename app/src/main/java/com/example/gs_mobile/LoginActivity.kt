package com.example.gs_mobile


import CadastroActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import android.util.Log

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var cbRememberMe: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializando o Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Altera a cor da barra de status
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBackground)

        // Configurações de elementos da interface
        val textCadastro = findViewById<TextView>(R.id.textCadastro)
        val textEsqueceuSenha = findViewById<TextView>(R.id.textEsqueceuSenha)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        cbRememberMe = findViewById(R.id.cbRememberMe)

        // Carrega o e-mail salvo (se existir) ao iniciar o aplicativo
        loadRememberedEmail()

        // Redireciona para a tela de cadastro
        textCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        // Redireciona para a tela de redefinição de senha
        textEsqueceuSenha.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        // Autenticação de login com Firebase
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                            // Salva o e-mail se o checkbox "Lembre de mim" estiver marcado
                            rememberEmail(cbRememberMe.isChecked)
                            // Redireciona para a HomeActivity
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish() // Fecha a tela de login
                        } else {
                            try {
                                throw task.exception ?: Exception("Erro desconhecido")
                            } catch (e: FirebaseAuthInvalidUserException) {
                                Toast.makeText(this, "Usuário não encontrado. Verifique o e-mail.", Toast.LENGTH_SHORT).show()
                            } catch (e: FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(this, "Senha incorreta. Tente novamente.", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(this, "Erro no login: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            }
        }
    }

    // Função para salvar o e-mail quando o checkbox está marcado
    private fun rememberEmail(shouldRemember: Boolean) {
        val sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (shouldRemember) {
            editor.putString("rememberedEmail", etEmail.text.toString())
        } else {
            editor.remove("rememberedEmail")
        }
        editor.apply()
    }

    // Função para carregar o e-mail salvo, se houver
    private fun loadRememberedEmail() {
        val sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val rememberedEmail = sharedPreferences.getString("rememberedEmail", null)
        if (rememberedEmail != null) {
            etEmail.setText(rememberedEmail)
            cbRememberMe.isChecked = true
        }
    }
}
