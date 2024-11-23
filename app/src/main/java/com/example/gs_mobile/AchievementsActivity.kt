package com.example.gs_mobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gs_mobile.HomeActivity
import com.example.gs_mobile.R
import com.example.prospapp.MonitoringActivity
import com.example.prospapp.UserProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.prospapp.api.ProspecoApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AchievementsActivity : AppCompatActivity() {

    private lateinit var apiService: ProspecoApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        // Configurar o Retrofit para a API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://prospecoapi.azurewebsites.net/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        apiService = retrofit.create(ProspecoApiService::class.java)

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

        // Obter o usuário atual, por exemplo, de uma variável compartilhada ou preferências
        val usuarioId = "usuario_id_aqui" // Substitua com o ID real do usuário

        // Chamar a função para carregar as metas e conquistas
        loadUserAchievements(usuarioId, badgesLayout, rankingLayout)
    }

    private fun loadUserAchievements(usuarioId: String, badgesLayout: LinearLayout, rankingLayout: LinearLayout) {
        lifecycleScope.launch {
            try {
                // Obter as metas do usuário
                val metasResponse = apiService.getMetasByUsuarioId(usuarioId)
                if (metasResponse.success) {
                    val metas = metasResponse.data as? List<String> ?: emptyList() // Conversão segura para List<String>
                    populateBadgesSection(metas, badgesLayout)
                } else {
                    Log.e("Achievements", "Falha ao carregar metas")
                }

                // Obter as conquistas do usuário
                val conquistasResponse = apiService.getConquistasByUsuarioId(usuarioId)
                if (conquistasResponse.success) {
                    val conquistas = conquistasResponse.data as? List<String> ?: emptyList() // Conversão segura para List<String>
                    populateRankingSection(conquistas, rankingLayout)
                } else {
                    Log.e("Achievements", "Falha ao carregar conquistas")
                }

            } catch (e: Exception) {
                Log.e("Achievements", "Erro ao carregar conquistas e metas", e)
            }
        }
    }

    // Função para popular a seção de Badges
    private fun populateBadgesSection(metas: List<String>?, badgesLayout: LinearLayout) {
        badgesLayout.removeAllViews() // Limpar a seção antes de adicionar novos badges

        metas?.forEach { meta ->
            val badgeTextView = TextView(this)
            badgeTextView.text = meta
            badgeTextView.textSize = 16f
            badgeTextView.setTextColor(resources.getColor(R.color.black)) // Defina a cor desejada
            badgesLayout.addView(badgeTextView)
        }
    }

    @SuppressLint("WrongViewCast")
    private fun populateRankingSection(conquistas: List<String>?, rankingLayout: LinearLayout) {
        rankingLayout.removeAllViews() // Limpar a seção antes de adicionar novos rankings

        conquistas?.forEachIndexed { index, conquista ->
            val rankingTextView = TextView(this)
            rankingTextView.text = "${index + 1}. $conquista" // Exibe a posição do ranking
            rankingTextView.textSize = 16f
            rankingTextView.setTextColor(resources.getColor(R.color.black)) // Defina a cor desejada
            rankingLayout.addView(rankingTextView)

            val consumptionChartImageView = findViewById<ImageView>(R.id.sectionConsumptionChart)
            consumptionChartImageView.setImageResource(R.drawable.ic_placeholder_chart)
        }
    }
}

