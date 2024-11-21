package com.example.gs_mobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import com.example.gs_mobile.R
import com.example.prospapp.MonitoringActivity
import com.example.prospapp.UserProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AchievementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        // Configuração do botão de perfil
        val btnProfile = findViewById<ImageButton>(R.id.btnProfile)
        btnProfile.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }

        // Configuração do BottomNavigationView para navegar de volta à Home ou para outras telas
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_home -> {
                    Log.d("Navigation", "Item Home selecionado")
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.item_monitoring -> {
                    Log.d("Navigation", "Item Monitoring selecionado")
                    startActivity(Intent(this, MonitoringActivity::class.java))
                    true
                }
                R.id.item_achievements -> {
                    Log.d("Navigation", "Item Achievements selecionado")
                    true
                }
                else -> {
                    Log.d("Navigation", "Item desconhecido selecionado")
                    false
                }
            }
        }

        // Obtendo as referências para os layouts que vão exibir os badges e ranking
        val badgesLayout = findViewById<LinearLayout>(R.id.sectionScores)
        val rankingLayout = findViewById<LinearLayout>(R.id.sectionCommunityRanking)

        // Preenchendo a seção de badges
        populateBadgesSection(badgesLayout)

        // Preenchendo a seção de ranking
        populateRankingSection(rankingLayout)
    }

    // Função para popular a seção de Badges
    private fun populateBadgesSection(badgesLayout: LinearLayout) {
        val badgesData = getBadgesData()
        for (badge in badgesData) {
            val badgeTextView = TextView(this)
            badgeTextView.text = badge
            badgeTextView.textSize = 16f
            badgeTextView.setTextColor(resources.getColor(R.color.black)) // Defina a cor desejada
            badgesLayout.addView(badgeTextView)
        }
    }

    // Função para popular a seção de Ranking
    private fun populateRankingSection(rankingLayout: LinearLayout) {
        val rankingData = getRankingData()
        for (ranking in rankingData) {
            val rankingTextView = TextView(this)
            rankingTextView.text = ranking
            rankingTextView.textSize = 16f
            rankingTextView.setTextColor(resources.getColor(R.color.black)) // Defina a cor desejada
            rankingLayout.addView(rankingTextView)
        }
    }

    // Dados simulados para demonstração
    private fun getBadgesData(): List<String> {
        return listOf("Selo de Economia", "Selo de Sustentabilidade", "Meta Mensal Batida")
    }

    private fun getRankingData(): List<String> {
        return listOf("1. Usuário A", "2. Usuário B", "3. Usuário C")
    }
}
