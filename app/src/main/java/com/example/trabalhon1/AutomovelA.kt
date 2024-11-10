package com.example.trabalhon1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AutomovelA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_automovel)

        val marca = intent.getStringExtra("marca")
        val modelo = intent.getStringExtra("modelo")
        val ano = intent.getIntExtra("ano", 0)
        val cor = intent.getStringExtra("cor")
        val id = intent.getIntExtra("id", 0)


        val tvMarca = findViewById<TextView>(R.id.tvMarca)
        val tvModelo = findViewById<TextView>(R.id.tvModelo)
        val tvAno = findViewById<TextView>(R.id.tvAno)
        val tvCor = findViewById<TextView>(R.id.tvCor)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        val imgMarca = findViewById<ImageView>(R.id.imgMarca)
        val btnDeletar = findViewById<Button>(R.id.btnDeletar)

        tvMarca.text = getString(R.string.tvMarca) + ": " + marca
        tvModelo.text = getString(R.string.tvModelo) + ": " + modelo
        tvAno.text = getString(R.string.tvAno) + ": " + ano
        tvCor.text = getString(R.string.tvCor) + ": " + cor

        val resId = resources.getIdentifier(marca?.lowercase(), "drawable", packageName)
        if (resId != 0) {
            imgMarca.setImageResource(resId)
        } else {
            imgMarca.setImageResource(R.drawable.default_img)
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(this, "Imagem da marca não encontrada!", Toast.LENGTH_SHORT).show()
            }, 3000) // teste
        }

        btnVoltar.setOnClickListener {
            finish()
        }

        btnDeletar.setOnClickListener{
            AlertDialog.Builder(this).apply {
                setTitle("Deletar")
                setMessage("Deseja remover este automovel?")
                setPositiveButton("Sim") { _, _ ->
                    AutoD.delete(this@AutomovelA, id)
                    Toast.makeText(this@AutomovelA, "Automóvel deletado!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@AutomovelA, Listagem::class.java)
                    startActivity(intent)
                    finish()
                }
                setNegativeButton("Não", null)
                show()
            }
        }
    }
}

