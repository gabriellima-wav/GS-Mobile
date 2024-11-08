// HeaderView.kt
package com.example.gs_mobile

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.content.Intent
import com.example.gs_mobile.R

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
            // Inicia a página de perfil do usuário
            val intent = Intent(context, UserProfileActivity::class.java)
            context.startActivity(intent)
        }
    }
}
