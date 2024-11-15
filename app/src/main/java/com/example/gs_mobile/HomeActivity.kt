package com.example.gs_mobile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.prospapp.MonitoringActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Configuração da cor da StatusBar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }

        // Configuração inicial das seções
        val tvConsumptionOverview: TextView = findViewById(R.id.tvConsumptionOverview)
        val tvTariffDetails: TextView = findViewById(R.id.tvTariffDetails)
        val tvTipsDetails: TextView = findViewById(R.id.tvTipsDetails)
        val progressMonthlyGoal: ProgressBar = findViewById(R.id.progressMonthlyGoal)

        // Exemplo de preenchimento de dados
        tvConsumptionOverview.text = "Consumo atual: 150 kWh"
        tvTariffDetails.text = "Bandeira atual: Amarela"
        tvTipsDetails.text = "Dica: Use a máquina de lavar antes das 18h."
        progressMonthlyGoal.progress = 60

        // Configuração do BottomNavigationView
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_home -> {
                    // Permanecer na tela atual
                    true
                }
                R.id.item_monitoring -> {
                    // Navegar para a tela de perfil
                    startActivity(Intent(this, MonitoringActivity::class.java))
                    true
                }
                R.id.item_achievements -> {
                    // Navegar para a tela de configurações
                    startActivity(Intent(this, AchievementsActivity
                    ::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
