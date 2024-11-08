package com.example.gs_mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Altere a cor da barra de status para uma cor específica
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBackground)
    }
}
