package com.example.prospapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat // Import necessário
import com.example.gs_mobile.MainActivity
import com.example.gs_mobile.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class UserProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Define a cor da status bar
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        auth = FirebaseAuth.getInstance()

        val newPasswordEditText: EditText = findViewById(R.id.newPasswordEditText)
        val changePasswordButton: Button = findViewById(R.id.changePasswordButton)
        val deleteAccountButton: Button = findViewById(R.id.deleteAccountButton)

        // Alterar senha
        changePasswordButton.setOnClickListener {
            val newPassword = newPasswordEditText.text.toString().trim()
            if (newPassword.isNotEmpty()) {
                showConfirmationDialog("Alterar Senha", "Deseja alterar sua senha?") {
                    changePassword(newPassword)
                }
            } else {
                Toast.makeText(this, "Insira uma nova senha!", Toast.LENGTH_SHORT).show()
            }
        }

        // Excluir conta
        deleteAccountButton.setOnClickListener {
            showConfirmationDialog("Excluir Conta", "Deseja excluir sua conta permanentemente?") {
                deleteAccount()
            }
        }
    }

    private fun showConfirmationDialog(title: String, message: String, onConfirm: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Sim") { dialog, _ ->
                onConfirm()
                dialog.dismiss()
            }
            .setNegativeButton("Não") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun changePassword(newPassword: String) {
        val user = auth.currentUser
        user?.updatePassword(newPassword)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Erro ao alterar senha!", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(this, "Usuário não autenticado!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteAccount() {
        val user = auth.currentUser
        if (user != null) {
            val passwordInput = EditText(this)
            passwordInput.hint = "Digite sua senha"
            passwordInput.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD

            AlertDialog.Builder(this)
                .setTitle("Confirmação")
                .setMessage("Para excluir sua conta, digite sua senha.")
                .setView(passwordInput)
                .setPositiveButton("Confirmar") { dialog, _ ->
                    val password = passwordInput.text.toString().trim()

                    if (password.isNotEmpty()) {
                        val credential = EmailAuthProvider.getCredential(user.email!!, password)
                        user.reauthenticate(credential).addOnCompleteListener { reauthTask ->
                            if (reauthTask.isSuccessful) {
                                user.delete().addOnCompleteListener { deleteTask ->
                                    if (deleteTask.isSuccessful) {
                                        Toast.makeText(this, "Conta excluída com sucesso!", Toast.LENGTH_SHORT).show()
                                        startActivity(Intent(this, MainActivity::class.java))
                                        finish()
                                    } else {
                                        Toast.makeText(this, "Erro ao excluir conta!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                Toast.makeText(this, "Senha incorreta! Tente novamente.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Senha não pode estar vazia!", Toast.LENGTH_SHORT).show()
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        } else {
            Toast.makeText(this, "Usuário não autenticado!", Toast.LENGTH_SHORT).show()
        }
    }

}
