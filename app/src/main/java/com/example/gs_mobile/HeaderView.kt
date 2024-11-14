package com.example.gs_mobile

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
        val ivUserIcon: ImageView = findViewById(R.id.ivUserIcon)
        ivUserIcon.setOnClickListener {
            try {
                // Verifica se a navegação para a UserProfileActivity está funcionando
                val intent = Intent(context, UserProfileActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } catch (e: Exception) {
                // Caso haja algum erro, exibe uma mensagem
                Toast.makeText(context, "Erro ao abrir o perfil do usuário", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
}

