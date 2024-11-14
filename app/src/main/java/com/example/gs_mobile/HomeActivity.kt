package com.example.gs_mobile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.colorPrimary) // Defina sua cor aqui
        }

        // Configuração do botão de perfil
        val btnProfile: ImageButton = findViewById(R.id.btnProfile)
        btnProfile.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }

        // Configuração inicial das seções
        val tvConsumptionOverview: TextView = findViewById(R.id.tvConsumptionOverview)
        val tvTariffDetails: TextView = findViewById(R.id.tvTariffDetails)
        val tvTipsDetails: TextView = findViewById(R.id.tvTipsDetails)
        val progressMonthlyGoal: ProgressBar = findViewById(R.id.progressMonthlyGoal)

        // Exemplo: Preenchendo dados (API será integrada futuramente)
        tvConsumptionOverview.text = "Consumo atual: 150 kWh"
        tvTariffDetails.text = "Bandeira atual: Amarela"
        tvTipsDetails.text = "Dica: Use a máquina de lavar antes das 18h."
        progressMonthlyGoal.progress = 60 // Exemplo de progresso
    }
}
