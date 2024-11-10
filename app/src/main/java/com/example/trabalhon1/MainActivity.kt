package com.example.trabalhon1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    private lateinit var btnListagem: Button
    private lateinit var btnSalvar: Button
    private lateinit var etMarca: EditText
    private lateinit var etModelo: EditText
    private lateinit var etAno: EditText
    private lateinit var etCor: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnListagem = findViewById(R.id.btnListagem)
        btnSalvar = findViewById(R.id.btnSalvar)
        etMarca = findViewById(R.id.etMarca)
        etModelo = findViewById(R.id.etModelo)
        etAno = findViewById(R.id.etAno)
        etCor = findViewById(R.id.etCor)


       btnSalvar.setOnClickListener{
            addAutomovel()
        }

        btnListagem.setOnClickListener {
            val intent = Intent(this, Listagem::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

    }

    private fun addAutomovel() {
        val marca = etMarca.text.toString()
        val modelo = etModelo.text.toString()
        val anoStr = etAno.text.toString()
        val cor = etCor.text.toString()

        if (marca.isEmpty() || modelo.isEmpty() || anoStr.isEmpty() || cor.isEmpty()) {
            Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_LONG).show()
        } else {
            val ano = anoStr.toIntOrNull()
            if (ano == null) {
                Toast.makeText(this, "O campo ano deve ser um número válido!", Toast.LENGTH_LONG).show()
                return
            }

            if (AutoD.aExist(this, modelo)) {
                Toast.makeText(this, "Este automóvel já existe!", Toast.LENGTH_SHORT).show()
            } else {
                val auto = Automovel(marca, modelo, ano, cor)
                AutoD.insert(this, auto)
 // TESTAR            loadAutomoveis()
                etMarca.setText("")
                etModelo.setText("")
                etAno.setText("")
                etCor.setText("")
                Toast.makeText(this, "Automovel adicionado!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}