package com.example.gs_mobile

    import android.os.Bundle
    import android.service.notification.NotificationListenerService
    import androidx.appcompat.app.AppCompatActivity
    import androidx.compose.material3.Badge
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.example.gs_mobile.R
    import com.example.prospapp.models.BadgesAdapter
    import com.example.prospapp.models.RankingAdapter

    class AchievementsActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_achievements)

            val recyclerViewBadges = findViewById<RecyclerView>(R.id.recyclerViewBadges)
            val recyclerViewRanking = findViewById<RecyclerView>(R.id.recyclerViewRanking)

            // Configurando o LayoutManager
            recyclerViewBadges.layoutManager = LinearLayoutManager(this)
            recyclerViewRanking.layoutManager = LinearLayoutManager(this)

            // Configurando os Adapters
            val badgesAdapter = BadgesAdapter(getBadgesData())
            val rankingAdapter = RankingAdapter(getRankingData())

            recyclerViewBadges.adapter = badgesAdapter
            recyclerViewRanking.adapter = rankingAdapter
        }

        // Dados simulados para demonstração
        private fun getBadgesData(): List<String> {
            return listOf("Selo de Economia", "Selo de Sustentabilidade", "Meta Mensal Batida")
        }

        private fun getRankingData(): List<String> {
            return listOf("1. Usuário A", "2. Usuário B", "3. Usuário C")
        }
    }
