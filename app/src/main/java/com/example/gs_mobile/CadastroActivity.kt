import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.gs_mobile.HomeActivity
import com.example.gs_mobile.R
import com.example.gs_mobile.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etNome: EditText  // Novo campo para o nome
    private lateinit var etSobrenome: EditText  // Novo campo para o sobrenome
    private lateinit var etEndereco: EditText  // Novo campo para o endereço

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Definir a cor da barra de status
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBackground)

        // Inicializar elementos de interface
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        etNome = findViewById(R.id.etNome)  // Inicializando o campo de nome
        etSobrenome = findViewById(R.id.etSobrenome)  // Inicializando o campo de sobrenome
        etEndereco = findViewById(R.id.etEndereco)  // Inicializando o campo de endereço
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        // Configurar o clique no botão de cadastro
        btnCadastrar.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // Verificar se todos os campos estão preenchidos
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                etNome.text.isEmpty() || etSobrenome.text.isEmpty() || etEndereco.text.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "As senhas não coincidem. Tente novamente.", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(email, password)
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Cadastro bem-sucedido
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()

                    // Obter o ID do usuário atual
                    val userId = auth.currentUser?.uid

                    // Criar um objeto Usuario com os dados do formulário
                    val nome = etNome.text.toString().trim()
                    val sobrenome = etSobrenome.text.toString().trim()
                    val endereco = etEndereco.text.toString().trim()

                    val usuario = Usuario(nome, sobrenome, email, endereco)

                    // Salvar os dados no Firebase Realtime Database
                    val database = FirebaseDatabase.getInstance()
                    val userRef = database.getReference("usuarios").child(userId!!)

                    // Salvar as informações do usuário, exceto a senha
                    userRef.setValue(usuario)
                        .addOnCompleteListener { dbTask ->
                            if (dbTask.isSuccessful) {
                                // Limpar campos ou redirecionar para outra tela
                                etEmail.text.clear()
                                etPassword.text.clear()
                                etConfirmPassword.text.clear()  // Limpar também o campo de confirmação de senha

                                // Redirecionar para a HomeActivity
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                                finish()  // Fecha a tela de cadastro
                            } else {
                                // Erro ao salvar dados no Firebase Realtime Database
                                Toast.makeText(this, "Erro ao salvar dados: ${dbTask.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                } else {
                    // Falha no cadastro
                    Toast.makeText(this, "Erro ao cadastrar: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}
