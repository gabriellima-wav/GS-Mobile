package com.example.gs_mobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.header_layout, this, true)

        // Referencia o ícone de usuário e adiciona o listener de clique
        val ivUserIcon: ImageView = findViewById(R.id.ivUserIcon)  // Acessa o ImageView pelo ID

        ivUserIcon.setOnClickListener {
            try {
                // Verifica se o contexto é uma Activity antes de iniciar a nova Activity
                if (context is Activity) {
                    val intent = Intent(context, UserProfileActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "Contexto não é uma Activity", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Caso haja algum erro, exibe uma mensagem
                Toast.makeText(context, "Erro ao abrir o perfil do usuário", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
}
