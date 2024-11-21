import android.content.Intent
import android.os.Bundle
import android.util.Log // Importar Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gs_mobile.LoginActivity
import com.example.gs_mobile.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity" // Tag personalizada para os logs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuração de margens para barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Carrega e inicia a animação de rotação na logo
        val logoImageView = findViewById<ImageView>(R.id.logoImageView)
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_logo)
        logoImageView.startAnimation(rotateAnimation)

        // Configura o botão para navegar para LoginActivity
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        btnEntrar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Testar a conexão com o banco de dados Oracle
        testOracleConnection()
    }

    private fun testOracleConnection() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Configurações do banco
                val url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521/orcl"
                val user = "rm552247"
                val password = "111205"

                Log.d(TAG, "Tentando conectar ao banco de dados: $url com usuário: $user")

                // Conexão
                val connection: Connection = DriverManager.getConnection(url, user, password)

                withContext(Dispatchers.Main) {
                    Log.d(TAG, "Conexão bem-sucedida!")
                    Toast.makeText(this@MainActivity, "Conexão bem-sucedida!", Toast.LENGTH_SHORT).show()
                }

                connection.close()
                Log.d(TAG, "Conexão fechada com sucesso.")
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao conectar ao banco de dados", e) // Log completo do erro no Logcat
                val errorMsg = "Erro: ${e.javaClass.simpleName} - ${e.message}"
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
