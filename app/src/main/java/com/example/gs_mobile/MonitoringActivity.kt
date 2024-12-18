package com.example.prospapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gs_mobile.AchievementsActivity
import com.example.gs_mobile.HomeActivity
import com.example.gs_mobile.R
import com.example.prospapp.adapters.ApplianceAdapter
import com.example.prospapp.models.Appliance
import com.google.android.material.bottomnavigation.BottomNavigationView

class MonitoringActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ApplianceAdapter
    private lateinit var addItemButton: Button
    private lateinit var profileImageButton: ImageButton
    private lateinit var bottomNavigationView: BottomNavigationView

    // Lista que será monitorada
    private val appliances = mutableListOf<Appliance>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitoring)

        // Definir a cor da Status Bar
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }

        recyclerView = findViewById(R.id.appliancesRecyclerView)
        addItemButton = findViewById(R.id.addItemButton)
        profileImageButton = findViewById(R.id.btnProfile)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        setupRecyclerView()

        // Configurar listener de navegação na BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.item_achievements -> {
                    startActivity(Intent(this, AchievementsActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Adicionar item na lista
        addItemButton.setOnClickListener {
            if (appliances.size < 20) {
                showAddItemDialog()
            } else {
                Toast.makeText(this, "Máximo de 20 itens atingido!", Toast.LENGTH_SHORT).show()
            }
        }

        // Navegar para a tela de perfil
        profileImageButton.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        adapter = ApplianceAdapter(appliances) { position ->
            showEditOrRemoveDialog(position)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun showAddItemDialog() {
        val input = EditText(this).apply {
            hint = "Nome do Eletrodoméstico"
        }

        AlertDialog.Builder(this)
            .setTitle("Novo Eletrodoméstico")
            .setMessage("Digite o nome do eletrodoméstico:")
            .setView(input)
            .setPositiveButton("Adicionar") { dialog, _ ->
                val itemName = input.text.toString().trim()
                if (itemName.isNotEmpty()) {
                    appliances.add(Appliance(name = itemName, status = "Desligado", powerUsage = 0.0))
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "Nome não pode estar vazio!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun showEditOrRemoveDialog(position: Int) {
        val appliance = appliances[position]
        val options = arrayOf("Editar", "Remover")

        AlertDialog.Builder(this)
            .setTitle("Gerenciar Item")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showEditItemDialog(position)
                    1 -> removeItem(position)
                }
            }
            .create()
            .show()
    }

    private fun showEditItemDialog(position: Int) {
        val appliance = appliances[position]
        val input = EditText(this).apply {
            hint = "Novo nome do Eletrodoméstico"
            setText(appliance.name)
        }

        AlertDialog.Builder(this)
            .setTitle("Editar Eletrodoméstico")
            .setMessage("Atualize o nome do eletrodoméstico:")
            .setView(input)
            .setPositiveButton("Salvar") { dialog, _ ->
                val newName = input.text.toString().trim()
                if (newName.isNotEmpty()) {
                    appliances[position] = appliance.copy(name = newName)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "Nome não pode estar vazio!", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun removeItem(position: Int) {
        appliances.removeAt(position)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Item removido.", Toast.LENGTH_SHORT).show()
    }
}
